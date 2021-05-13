package com.nqminhuit.gateway.configs;

import com.nqminhuit.voucherShared.configs.CentralKafkaConsumerConfig;
import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig extends BaseConfig {

    @Bean
    public ConsumerFactory<String, ReceiveCodeMsg> receiveCodeMsgConsumerFactory() {
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
