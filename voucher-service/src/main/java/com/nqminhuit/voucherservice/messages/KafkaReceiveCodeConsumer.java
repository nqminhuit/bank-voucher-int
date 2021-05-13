package com.nqminhuit.voucherservice.messages;

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
        groupId = "rec-group-db",
        containerFactory = KafkaTopicConstants.RECEIVE_CODE_LISTENER_CONTAINER_FACTORY)
    public void listenToReceiveCodeMsg(ReceiveCodeMsg msg) {
        log.info("voucher-service also recive code msg: {}", msg);
    }

}
