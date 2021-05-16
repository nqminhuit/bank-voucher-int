package com.nqminhuit.voucherintservice.clients;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VoucherProviderClient {

    private static final Logger log = LoggerFactory.getLogger(VoucherProviderClient.class);

    public HttpResponse<String> requestForVoucherCode() {
        var postRequest = HttpRequest.newBuilder()
            .POST(BodyPublishers.ofString(bodyRequest()))
            .uri(URI.create("http://localhost:8081/api/request/voucher"))
            .setHeader("user-agent", "Java 11 HttpClient Bot")
            .header("content-type", "application/json")
            .build();

        try {
            HttpResponse<String> response = HttpClient.newBuilder()
                .version(Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(5))
                .build()
                .send(postRequest, HttpResponse.BodyHandlers.ofString());
            log.info("Response code: {}", response.statusCode());
            log.info("Response body: {}", response.body());
            return response;
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    private String bodyRequest() {
        try {
            return new ObjectMapper().writeValueAsString(Map.ofEntries(
                Map.entry("phoneNumber", "0908123456"),
                Map.entry("callbackUrl", URI.create("http://localhost:8082/api/voucher-code/vps/response"))));
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
