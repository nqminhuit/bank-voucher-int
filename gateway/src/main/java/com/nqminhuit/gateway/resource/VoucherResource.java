package com.nqminhuit.gateway.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoucherResource {

    @PostMapping("/voucher")
    ResponseEntity<String> requestVoucher() {
        return ResponseEntity.ok("Your request is being processed within 30 seconds");
    }

}
