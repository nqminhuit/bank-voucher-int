package com.nqminhuit.voucherintservice.http_clients.config;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HttpClientConfig {

    @Bean
    public HttpClient clientConfig() {
        return HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    }

}
