package com.nqminhuit.voucherShared.configs;

import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public abstract class BaseConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    protected String bootstrapServer;

    protected ConsumerFactory<String, ReceiveCodeMsg> receiveCodeMsgConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
            CentralKafkaConsumerConfig.consumerConfigs(bootstrapServer),
            new StringDeserializer(),
            new JsonDeserializer<>(ReceiveCodeMsg.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReceiveCodeMsg> receiveCodeMsgListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ReceiveCodeMsg> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(receiveCodeMsgConsumerFactory());
        return factory;
    }
}
