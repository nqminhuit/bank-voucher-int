package com.nqminhuit.voucherproviderserver.controllers;

import com.nqminhuit.voucherproviderserver.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class VoucherRequestControllers {

    @Autowired
    private VoucherService voucherService;

    @PostMapping("/request/voucher")
    public Mono<String> handleVoucherRequest() {
        return voucherService.generateVoucherCode();
    }
}
