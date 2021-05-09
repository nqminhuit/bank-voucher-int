package com.nqminhuit.voucherintservice.messages;

import com.nqminhuit.voucherintservice.clients.VoucherProviderClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaRequestCodeConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaRequestCodeConsumer.class);

    @KafkaListener(topics = "request-code", groupId = "req-group")
    public void listenToRequestCode(String message) {
        log.info("listen to request code message: {}", message);
        String response = VoucherProviderClient.requestForVoucherCode();
        log.info("response: {}", response);
    }

}
