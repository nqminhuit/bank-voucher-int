package com.nqminhuit.voucherservice.controllers;

import java.util.List;
import com.nqminhuit.voucherservice.domain.dtos.VoucherDto;
import com.nqminhuit.voucherservice.services.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VoucherControllers {

    private static final Logger log = LoggerFactory.getLogger(VoucherControllers.class);

    @Autowired
    private VoucherService voucherService;

    @GetMapping("/voucher/{phoneNumber}")
    public ResponseEntity<List<VoucherDto>> findAllVouchersByPhoneNumber(@PathVariable String phoneNumber) {
        log.info("Receives request to find all vouchers by phoneNumber '{}'", phoneNumber);
        List<VoucherDto> vouchers = voucherService.findAllVouchersByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(vouchers);
    }

}
