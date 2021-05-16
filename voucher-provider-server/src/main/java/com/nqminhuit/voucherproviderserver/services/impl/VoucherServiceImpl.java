package com.nqminhuit.voucherproviderserver.services.impl;

import com.nqminhuit.voucherproviderserver.controllers.models.RequestVoucherModel;
import com.nqminhuit.voucherproviderserver.services.VoucherService;
import com.nqminhuit.voucherproviderserver.services.impl.constants.ClientTimer;
import com.nqminhuit.voucherproviderserver.services.impl.constants.MessageResponse;
import com.nqminhuit.voucherproviderserver.services.impl.threads.VoucherGeneratorThread;
import com.nqminhuit.voucherproviderserver.utils.ThreadUtils;
import com.voucher.provider.models.ResponseVoucherModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VoucherServiceImpl implements VoucherService {

    private static final Logger log = LoggerFactory.getLogger(VoucherServiceImpl.class);

    @Override
    public ResponseVoucherModel generateVoucherCode(RequestVoucherModel req) {
        var phoneNumber = req.getPhoneNumber();
        log.info("generate voucher code for phoneNumber: {}", phoneNumber);

        var start = System.currentTimeMillis();
        var futureResponse = VoucherGeneratorThread.generate(start, phoneNumber, req.getCallbackUrl());

        while (!futureResponse.isDone()) {
            if (System.currentTimeMillis() - start >= ClientTimer.MAX_CLIENT_WAIT_MILLIS) {
                log.info("Timeout! Response pending message.");
                return ResponseVoucherModel.builder()
                    .buildResponseTimeout(MessageResponse.MSG_RESPONSE_CLIENT_WAIT);
            }
            ThreadUtils.safeSleep(500);
        }

        log.info("Code is generated under 3s, return voucher code directly to client.");
        return ResponseVoucherModel.builder().buildResponseFromFuture(futureResponse);
    }

}
