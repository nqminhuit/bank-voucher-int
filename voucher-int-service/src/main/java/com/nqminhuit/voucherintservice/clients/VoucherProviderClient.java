package com.nqminhuit.voucherintservice.clients;

import com.nqminhuit.voucherintservice.messages.KafkaReceiveCodeProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class VoucherProviderClient {

    private static final Logger log = LoggerFactory.getLogger(VoucherProviderClient.class);

    private static WebClient vpsClient = WebClient.create("http://localhost:8081"); // TODO get from properties

    @Autowired
    private KafkaReceiveCodeProducer kafkaReceiveCodeProducer;

    public void requestForVoucherCode() {
        vpsClient.post()
        .uri("/api/request/voucher")
            .retrieve()
            .bodyToMono(String.class)
            .subscribe(
                data -> {
                    log.info("##new## data: {}", data);
                    if (data.startsWith("###code###")) {
                        kafkaReceiveCodeProducer.send("receive-code", "key", data);
                    }
                },
                error -> {
                    log.info("##error## {}", error);
                },
                () -> {
                    log.info("##completed##");
                }
            );
    }
}
