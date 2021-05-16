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

    public static void returnViaCallbackUrl(String callbackUrl, ResponseVoucherModel res)
        throws IOException, InterruptedException {

        log.info("Response data {} via callbackUrl {}", res, callbackUrl);
        var postRequest = HttpRequest.newBuilder()
            .POST(BodyPublishers.ofString(res.toJson()))
            .uri(URI.create(callbackUrl))
            .setHeader("User-Agent", "Java 11 HttpClient Bot")
            .header("content-type", "application/json")
            .build();

        log.info("before send request");
        var response = HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(5))
            .build()
            .send(postRequest, HttpResponse.BodyHandlers.ofString());
        log.info("got response");
        log.info("Response code: {}", response.statusCode());
        log.info("Response body: {}", response.body());
    }

}
