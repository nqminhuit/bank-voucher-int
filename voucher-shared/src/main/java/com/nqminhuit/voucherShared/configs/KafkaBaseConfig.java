package com.nqminhuit.voucherShared.configs;

import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public abstract class KafkaBaseConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    protected String bootstrapServer;

    private ConsumerFactory<String, ReceiveCodeMsg> receiveCodeMsgConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
            CentralKafkaConsumerConfig.jsonConsumerConfigs(bootstrapServer),
            new StringDeserializer(),
            new JsonDeserializer<>(ReceiveCodeMsg.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReceiveCodeMsg> receiveCodeMsgListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, ReceiveCodeMsg>();
        factory.setConsumerFactory(receiveCodeMsgConsumerFactory());
        return factory;
    }
}
