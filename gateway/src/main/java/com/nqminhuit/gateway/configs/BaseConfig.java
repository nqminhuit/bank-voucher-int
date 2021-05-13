package com.nqminhuit.gateway.configs;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    String bootstrapServer;

}
