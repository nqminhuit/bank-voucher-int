package com.nqminhuit.gateway.messages;

import com.nqminhuit.gateway.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiveCodeConsumer {

    Logger log = LoggerFactory.getLogger(KafkaReceiveCodeConsumer.class);

    // @KafkaListener(topics = Constants.TOPIC_REQUEST_CODE, groupId = "req-group")
    // public void listenToRequestCode(String message) {
    //     log.info("listen to request code message: {}", message);
    // }

    @KafkaListener(topics = Constants.TOPIC_RECEIVE_CODE, groupId = "rec-group")
    public void listenToReceiveCode(String message) {
        log.info("listen to receive code message: {}", message);
    }
}
