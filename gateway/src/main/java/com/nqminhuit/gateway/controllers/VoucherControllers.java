package com.nqminhuit.gateway.controllers;

import com.nqminhuit.gateway.messages.KafkaRequestCodeProducer;
import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
public class VoucherControllers {

    @Autowired
    private KafkaRequestCodeProducer kafkaProducer;

    @Autowired
    private Jedis jedis;

    @PostMapping("/voucher")
    ResponseEntity<String> requestVoucher(@RequestParam("phoneNumber") String phoneNumber,
        @RequestParam("callbackUrl") String callbackUrl) {
        kafkaProducer.send(KafkaTopicConstants.REQUEST_CODE, phoneNumber);
        jedis.setex(phoneNumber, 300L, callbackUrl);
        return ResponseEntity.ok("Your request is being processed...");
    }

}
