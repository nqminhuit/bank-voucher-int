package com.nqminhuit.gateway.resource;

import com.nqminhuit.gateway.messages.KafkaRequestCodeProducer;
import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoucherResource {

    @Autowired
    private KafkaRequestCodeProducer kafkaProducer;

    @PostMapping("/voucher")
    ResponseEntity<String> requestVoucher(@RequestParam("phoneNumber") String phoneNumber) {
        kafkaProducer.send(KafkaTopicConstants.REQUEST_CODE, phoneNumber);
        return ResponseEntity.ok("Your request is being processed...");
    }

}
