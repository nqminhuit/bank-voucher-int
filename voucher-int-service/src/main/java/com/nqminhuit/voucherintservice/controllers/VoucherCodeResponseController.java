package com.nqminhuit.voucherintservice.controllers;

import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import com.nqminhuit.voucherintservice.messages.KafkaReceiveCodeProducer;
import com.voucher.provider.models.ResponseVoucherModel;
import com.voucher.provider.models.enumerations.VoucherResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VoucherCodeResponseController {

    private static final Logger log = LoggerFactory.getLogger(VoucherCodeResponseController.class);

    @Autowired
    private KafkaReceiveCodeProducer kafkaReceiveCodeProducer;

    @PostMapping("/voucher-code/vps/response")
    public void handleResponseCallback(@RequestBody ResponseVoucherModel body) {
        log.info("handle voucher code response callback with body {}", body);

        // TODO impl security check for integrity data

        VoucherResponseStatus status = body.getVoucherResponseStatus();
        if (VoucherResponseStatus.SUCCESS.equals(status)
            || VoucherResponseStatus.LATE_SUCCESS.equals(status)) {
            var msg = new ReceiveCodeMsg(body.getPhoneNumber(), body.getCode(), status.name());
            log.info("sending to kafka receive code with message: {}", msg);
            kafkaReceiveCodeProducer.send(KafkaTopicConstants.RECEIVE_CODE, msg);
        }
    }

}
