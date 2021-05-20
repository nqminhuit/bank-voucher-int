package com.nqminhuit.voucherproviderserver.services.impl.threads;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import com.nqminhuit.voucherproviderserver.http_client.VoucherCodeClientCallback;
import com.nqminhuit.voucherproviderserver.services.impl.constants.ClientTimer;
import com.nqminhuit.voucherproviderserver.services.impl.utils.MessageResponseUtils;
import com.nqminhuit.voucherproviderserver.utils.NetworkSimulationUtils;
import com.voucher.provider.models.ResponseVoucherModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoucherGeneratorThread {

    private static final Logger log = LoggerFactory.getLogger(VoucherGeneratorThread.class);

    public static Future<ResponseVoucherModel> generate(
        long startTimeMillis, String phoneNumber, String callbackUrl) {

        Callable<ResponseVoucherModel> responseVoucherCode = () -> {
            NetworkSimulationUtils.delay();
            return generateResponseVoucherModel(startTimeMillis, phoneNumber, callbackUrl);
        };

        var executor = Executors.newSingleThreadExecutor();
        var futureResponse = executor.submit(responseVoucherCode);
        executor.shutdown(); // TODO: should we shutdown it or leave it for upcomming requests?
        return futureResponse;
    }

    // expose public for testable, TODO: may find other way
    public static ResponseVoucherModel generateResponseVoucherModel(
        long startTimeMillis, String phoneNumber, String callbackUrl) {

        var elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
        var res = ResponseVoucherModel.builder()
            .code(UUID.randomUUID().toString())
            .phoneNumber(phoneNumber)
            .message(MessageResponseUtils.chooseMessage(elapsedTimeMillis))
            .voucherResponseStatus(MessageResponseUtils.chooseStatus(elapsedTimeMillis))
            .codeVerifier("codeVerifier")
            .transformMethod("transformMethod")
            .build();
        log.info("Generated voucher code: {}", res);

        if (elapsedTimeMillis > ClientTimer.MAX_CLIENT_WAIT_MILLIS) {
            VoucherCodeClientCallback.returnViaCallbackUrl(callbackUrl, res);
            return null;
        }
        return res;
    }

}
