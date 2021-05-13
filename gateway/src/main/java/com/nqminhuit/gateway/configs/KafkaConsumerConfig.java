package com.nqminhuit.gateway.configs;

import java.util.HashMap;
import java.util.Map;
import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, ReceiveCodeMsg> receiveCodeMsgConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(
            props,
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
