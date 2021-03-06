package com.nqminhuit.gateway.messages.configs;

import com.nqminhuit.voucherShared.configs.CentralKafkaProducerConfig;
import com.nqminhuit.voucherShared.configs.KafkaBaseConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig extends KafkaBaseConfig {

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(
            CentralKafkaProducerConfig.stringProducerConfigs(bootstrapServer));
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String, String>(producerFactory());
    }

}
