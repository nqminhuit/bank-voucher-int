package com.nqminhuit.voucherproviderserver.services.impl.threads;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import com.nqminhuit.voucherproviderserver.controllers.models.ResponseVoucherModel;
import com.nqminhuit.voucherproviderserver.services.impl.constants.ClientTimer;
import com.nqminhuit.voucherproviderserver.services.impl.utils.MessageResponseUtils;
import com.nqminhuit.voucherproviderserver.utils.NetworkSimulationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoucherGeneratorThread {

    private static final Logger log = LoggerFactory.getLogger(VoucherGeneratorThread.class);

    public static Future<ResponseVoucherModel> generate(
        long startTimeMillis, String phoneNumber, String callbackUrl) {

        Callable<ResponseVoucherModel> responseVoucherCode = () -> {
            NetworkSimulationUtils.delay();
            long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
            ResponseVoucherModel res = ResponseVoucherModel.builder()
                .code(UUID.randomUUID().toString())
                .phoneNumber(phoneNumber)
                .message(MessageResponseUtils.chooseMessage(elapsedTimeMillis))
                .voucherResponseStatus(MessageResponseUtils.chooseStatus(elapsedTimeMillis))
                .build();
            log.info("Generated voucher code: {}", res);

            if (elapsedTimeMillis > ClientTimer.MAX_CLIENT_WAIT_MILLIS) {
                log.info("Response via callbackUrl");
                return null;
            }
            return res;
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ResponseVoucherModel> futureResponse = executor.submit(responseVoucherCode);
        executor.shutdown(); // TODO: should we shutdown it or leave it for other upcomming request?
        return futureResponse;
    }

}
