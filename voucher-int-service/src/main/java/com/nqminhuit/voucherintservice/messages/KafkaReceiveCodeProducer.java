package com.nqminhuit.voucherintservice.messages;

import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiveCodeProducer {

    private KafkaTemplate<String, ReceiveCodeMsg> kafkaTemplate;

    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, ReceiveCodeMsg> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, ReceiveCodeMsg msg) {
        kafkaTemplate.send(topic, msg);
    }
}
