package com.nqminhuit.gateway.rest_clients.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate configRestTemplate() {
        // this config requires org/apache/http/protocol/HttpContext class
        // var requestFactory = new HttpComponentsClientHttpRequestFactory();
        // requestFactory.setReadTimeout(5000);
        return new RestTemplate();
    }

}
