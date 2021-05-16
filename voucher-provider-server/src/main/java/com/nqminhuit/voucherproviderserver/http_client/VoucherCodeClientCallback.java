package com.nqminhuit.voucherproviderserver.http_client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.Duration;
import com.voucher.provider.models.ResponseVoucherModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoucherCodeClientCallback {

    private static final Logger log = LoggerFactory.getLogger(VoucherCodeClientCallback.class);

    public static void returnViaCallbackUrl(String callbackUrl, ResponseVoucherModel res) {
        var jsonBody = res.toJson();
        log.info("Response data {} via callbackUrl {}, with body {}", res, callbackUrl, jsonBody);
        var postRequest = HttpRequest.newBuilder()
            .POST(BodyPublishers.ofString(jsonBody))
            .uri(URI.create(callbackUrl))
            .setHeader("User-Agent", "Java 11 HttpClient Bot")
            .header("content-type", "application/json")
            .build();

        HttpResponse<String> response;
        try {
            response = HttpClient.newBuilder()
                .version(Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(5))
                .build()
                .send(postRequest, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException | InterruptedException e) {
            log.error("Exception happened: {}", e.getMessage());
            e.printStackTrace();
            return;
        }

        int responseCode = response.statusCode();
        log.info("Response code: {}", responseCode);
        if (isNotSuccessCode(responseCode)) {
            log.error("Request sent failed: {}", response);
        }
        else {
            log.info("Request sent successful: {}", response);
        }
    }

    private static boolean isNotSuccessCode(int code) {
        return code / 100 != 2;
    }

}
