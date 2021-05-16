package com.nqminhuit.voucherintservice.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import com.nqminhuit.voucherintservice.http_clients.VoucherProviderClient;
import com.voucher.provider.models.ResponseVoucherModel;
import com.voucher.provider.models.enumerations.VoucherResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaRequestCodeConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaRequestCodeConsumer.class);

    @Autowired
    private VoucherProviderClient vpsClient;

    @Autowired
    private KafkaReceiveCodeProducer kafkaReceiveCodeProducer;

    @Autowired
    private ObjectMapper jsonMapper;

    @KafkaListener(topics = KafkaTopicConstants.REQUEST_CODE, groupId = "req-group")
    public void listenToRequestCode(String phoneNumber) throws IllegalArgumentException {
        validatePhoneNumber(phoneNumber);

        var responseBody = vpsClient.requestForVoucherCode(phoneNumber).body(); // cant be null
        log.info("code response: {} from request for phoneNumber: {}", responseBody, phoneNumber);

        ResponseVoucherModel responseModel;
        try {
            responseModel = jsonMapper.readValue(responseBody, ResponseVoucherModel.class);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        if (responseModel == null) {
            log.error("Could not read JSON value from responseBody: {}", responseBody);
            return;
        }

        VoucherResponseStatus codeStatus = responseModel.getVoucherResponseStatus();
        if (VoucherResponseStatus.SUCCESS.equals(codeStatus)) { // TODO handle ERROR case
            var msg = new ReceiveCodeMsg(
                responseModel.getPhoneNumber(), responseModel.getCode(), codeStatus.name());
            log.info("sending to kafka receive code with message: {}", msg);
            kafkaReceiveCodeProducer.send(KafkaTopicConstants.RECEIVE_CODE, msg);
        }
    }

    private void validatePhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("phoneNumber must not be empty");
        }
        // TODO implement more cases
    }

}
