package com.nqminhuit.voucherproviderserver.controllers;

import com.nqminhuit.voucherproviderserver.controllers.models.RequestVoucherModel;
import com.nqminhuit.voucherproviderserver.services.VoucherService;
import com.voucher.provider.models.ResponseVoucherModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VoucherRequestControllers {

    private static final Logger log = LoggerFactory.getLogger(VoucherRequestControllers.class);

    @Autowired
    private VoucherService voucherService;

    @PostMapping("/request/voucher")
    public ResponseVoucherModel handleVoucherRequest(@RequestBody RequestVoucherModel req) {
        log.info("handle request to get new voucher code: {}", req);
        // TODO validate request:

        return voucherService.generateVoucherCode(req);
    }
}
