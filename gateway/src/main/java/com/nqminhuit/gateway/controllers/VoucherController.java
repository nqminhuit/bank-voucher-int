package com.nqminhuit.gateway.controllers;

import java.util.List;
import com.nqminhuit.gateway.messages.KafkaRequestCodeProducer;
import com.nqminhuit.gateway.rest_clients.VoucherServiceClient;
import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import com.nqminhuit.voucherShared.dtos.VoucherDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
public class VoucherController {

    private static final Logger log = LoggerFactory.getLogger(VoucherController.class);

    @Value("${bank-voucher-int.gateway.redisTtlSeconds}")
    private Long redisRecordTtl;

    @Autowired
    private KafkaRequestCodeProducer kafkaProducer;

    @Autowired
    private Jedis jedis;

    @Autowired
    private VoucherServiceClient voucherServiceClient;

    @PostMapping("/voucher")
    ResponseEntity<String> requestVoucher(@RequestParam("phoneNumber") String phoneNumber,
        @RequestParam("callbackUrl") String callbackUrl) {
        kafkaProducer.send(KafkaTopicConstants.REQUEST_CODE, phoneNumber);
        jedis.setex(phoneNumber, redisRecordTtl, callbackUrl);
        return ResponseEntity.ok("Your request is being processed...");
    }

    @GetMapping("/voucher/{phoneNumber}")
    List<VoucherDto> getAllVoucherCodes(@PathVariable String phoneNumber) {
        log.info("Receives request to get all voucher code by phoneNumber '{}'", phoneNumber);
        var vouchers = voucherServiceClient.findAllVouchersByPhoneNumber(phoneNumber);
        log.info("Receives result: {}", vouchers);
        return vouchers;
    }

}
