package com.nqminhuit.gateway.messages.configs;

import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicRequestCode() {
        return TopicBuilder.name(KafkaTopicConstants.REQUEST_CODE).build();
    }

    @Bean
    public NewTopic topicReceiveCode() {
        return TopicBuilder.name(KafkaTopicConstants.RECEIVE_CODE).build();
    }

}
