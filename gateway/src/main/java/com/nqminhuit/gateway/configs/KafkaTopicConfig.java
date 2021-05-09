package com.nqminhuit.gateway.configs;

import com.nqminhuit.gateway.common.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicRequestCode() {
        return TopicBuilder.name(Constants.TOPIC_REQUEST_CODE).build();
    }

    @Bean
    public NewTopic topicReceiveCode() {
        return TopicBuilder.name(Constants.TOPIC_RECEIVE_CODE).build();
    }

}