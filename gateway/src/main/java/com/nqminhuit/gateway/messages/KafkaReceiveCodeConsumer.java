package com.nqminhuit.gateway.messages;

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
        groupId = "rec-group",
        containerFactory = "receiveCodeMsgListenerContainerFactory")
    public void listenToReceiveCode(ReceiveCodeMsg message) {
        log.info("listen to receive code message: {}", message);
    }
}
