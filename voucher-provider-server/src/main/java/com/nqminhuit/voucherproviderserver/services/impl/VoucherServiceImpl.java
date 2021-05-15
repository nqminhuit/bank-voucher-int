package com.nqminhuit.voucherproviderserver.services.impl;

import com.nqminhuit.voucherproviderserver.controllers.models.RequestVoucherModel;
import com.nqminhuit.voucherproviderserver.controllers.models.ResponseVoucherModel;
import com.nqminhuit.voucherproviderserver.services.VoucherService;
import com.nqminhuit.voucherproviderserver.services.impl.threads.VoucherGeneratorThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VoucherServiceImpl implements VoucherService {

    private static final Logger log = LoggerFactory.getLogger(VoucherServiceImpl.class);

    @Override
    public ResponseVoucherModel generateVoucherCode(RequestVoucherModel req) {
        String phoneNumber = req.getPhoneNumber();
        log.info("generate voucher code for phoneNumber: {}", phoneNumber);
        VoucherGeneratorThread voucherGeneratorThread = new VoucherGeneratorThread(phoneNumber);
        voucherGeneratorThread.run();
        return null;
    }

}
