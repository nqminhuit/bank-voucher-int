package com.nqminhuit.voucherintservice.configs;

import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import com.nqminhuit.voucherShared.configs.CentralKafkaProducerConfig;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public ProducerFactory<String, ReceiveCodeMsg> receiveCodeMsgProducerFactory() {
        return new DefaultKafkaProducerFactory<>(CentralKafkaProducerConfig.producerConfigs(bootstrapServer));
    }

    @Bean
    public KafkaTemplate<String, ReceiveCodeMsg> receiveCodeMsgTemplate() {
        return new KafkaTemplate<>(receiveCodeMsgProducerFactory());
    }

}
