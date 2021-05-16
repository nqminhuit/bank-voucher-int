package com.nqminhuit.voucherintservice.controllers;

import com.voucher.provider.models.ResponseVoucherModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VoucherCodeResponseController {

    private static final Logger log = LoggerFactory.getLogger(VoucherCodeResponseController.class);

    @PostMapping("/voucher-code/vps/response")
    void handleResponseCallback(@RequestBody ResponseVoucherModel body) {
        log.info("handle request with body {}", body);
    }

}
