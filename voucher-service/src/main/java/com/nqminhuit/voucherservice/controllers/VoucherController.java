package com.nqminhuit.voucherservice.controllers;

import java.util.List;
import com.nqminhuit.voucherShared.dtos.VoucherDto;
import com.nqminhuit.voucherShared.dtos.VoucherDtosWrapper;
import com.nqminhuit.voucherservice.services.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VoucherController {

    private static final Logger log = LoggerFactory.getLogger(VoucherController.class);

    @Autowired
    private VoucherService voucherService;

    @GetMapping("/voucher/{phoneNumber}")
    public VoucherDtosWrapper findAllVouchersByPhoneNumber(@PathVariable String phoneNumber) {
        log.info("Receives request to find all vouchers by phoneNumber '{}'", phoneNumber);
        List<VoucherDto> voucherDtos = voucherService.findAllVouchersByPhoneNumber(phoneNumber);
        return new VoucherDtosWrapper(voucherDtos);
    }

}
