package com.nqminhuit.gateway.messages;

import com.nqminhuit.gateway.services.impl.ReturnCodeMethodFactory;
import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiveCodeConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaReceiveCodeConsumer.class);

    @KafkaListener(
        topics = KafkaTopicConstants.RECEIVE_CODE,
        groupId = "rec-group-dspl",
        containerFactory = KafkaTopicConstants.RECEIVE_CODE_LISTENER_CONTAINER_FACTORY)
    public void listenToReceiveCode(ReceiveCodeMsg message) {
        log.info("listen to receive code message: {}", message);
        var codeStatus = message.getStatus();
        if (codeStatus == null || codeStatus.isBlank()) {
            log.error("'status' field is missing!");
            return;
        }
        ReturnCodeMethodFactory.getMethod(codeStatus).returnVoucherCode(message.getVoucherCode());
        // TODO return msg to client via callback url
    }
}
