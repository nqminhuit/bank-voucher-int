package com.nqminhuit.gateway.resource;

import com.nqminhuit.gateway.common.Constants;
import com.nqminhuit.gateway.messages.KafkaRequestCodeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoucherResource {

    @Autowired
    private KafkaRequestCodeProducer kafkaProducer;

    @PostMapping("/voucher")
    ResponseEntity<String> requestVoucher() {
        kafkaProducer.send(Constants.TOPIC_REQUEST_CODE, "someone requests a voucher code!");
        return ResponseEntity.ok("Your request is being processed...");
    }

}
