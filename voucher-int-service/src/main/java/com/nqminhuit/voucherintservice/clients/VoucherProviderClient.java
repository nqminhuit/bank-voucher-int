package com.nqminhuit.voucherintservice.clients;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class VoucherProviderClient {
    private static WebClient client = WebClient.create("http://localhost:8081"); // TODO get from properties

    private static Mono<ClientResponse> result = client.post()
        .uri("/api/request/voucher")
        .accept(MediaType.TEXT_PLAIN)
        .exchange();

    public static String requestForVoucherCode() {
        return ">>> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();
    }
}
