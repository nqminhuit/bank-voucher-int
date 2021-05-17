package com.nqminhuit.voucherintservice.http_clients;

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
import com.nqminhuit.voucherintservice.http_clients.utils.HttpResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VoucherProviderClient {

    private static final Logger log = LoggerFactory.getLogger(VoucherProviderClient.class);

    private String voucherServerProviderUrl;

    private String requestProtocol;

    private Long serverPort;

    private String serverHostname;

    @Value("${voucher-int-service.voucher-provider-url}")
    public void setVoucherServerProviderUrl(String url) {
        this.voucherServerProviderUrl = url;
    }

    @Value("${voucher-int-service.request.protocol}")
    public void setRequestProtocol(String protocol) {
        this.requestProtocol = protocol;
    }

    @Value("${server.port}")
    public void setServerPort(Long port) {
        this.serverPort = port;
    }

    @Value("${server.hostName}")
    public void setServerHostname(String hostname) {
        this.serverHostname = hostname;
    }

    public HttpResponse<String> requestForVoucherCode(String phoneNumber) {
        var postRequest = HttpRequest.newBuilder()
            .POST(BodyPublishers.ofString(bodyRequest(phoneNumber)))
            .uri(URI.create(requestProtocol + "://" + this.voucherServerProviderUrl + "/api/request/voucher"))
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
            return HttpResponseUtils.response("Exception happened: " + e.getMessage(), 500);
        }
    }

    private String bodyRequest(String phoneNumber) {
        try {
            return new ObjectMapper().writeValueAsString(Map.ofEntries(
                Map.entry("phoneNumber", phoneNumber),
                Map.entry("callbackUrl", URI.create(
                    "http://" + serverHostname + ":" + serverPort + "/api/voucher-code/vps/response"))));
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
