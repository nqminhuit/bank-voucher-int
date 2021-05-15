package com.nqminhuit.voucherproviderserver.services.impl.threads;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.nqminhuit.voucherproviderserver.controllers.models.ResponseVoucherModel;
import com.nqminhuit.voucherproviderserver.services.enumerations.VoucherResponseStatus;
import com.nqminhuit.voucherproviderserver.services.impl.constants.ClientTimer;
import com.nqminhuit.voucherproviderserver.services.impl.constants.MessageResponse;
import com.nqminhuit.voucherproviderserver.services.impl.utils.MessageResponseUtils;
import com.nqminhuit.voucherproviderserver.utils.NetworkSimulationUtils;
import com.nqminhuit.voucherproviderserver.utils.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoucherGeneratorThread {

    private static final Logger log = LoggerFactory.getLogger(VoucherGeneratorThread.class);

    private ResponseVoucherModel res = null;

    private String phoneNumber;

    public VoucherGeneratorThread(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ResponseVoucherModel getResponseVoucherModel() {
        return this.res;
    }

    public void run() {
        long startTimeMillis = System.currentTimeMillis();
        Runnable responseTimeout = () -> {
            ThreadUtils.safeSleep(ClientTimer.MAX_CLIENT_WAIT_MILLIS);
            if (this.res != null) {
                return;
            }
            this.res = new ResponseVoucherModel();
            this.res.setMessage(MessageResponse.MSG_RESPONSE_CLIENT_WAIT);
            this.res.setVoucherResponseStatus(VoucherResponseStatus.PENDING);
            log.info("client wait timeout, response: {}", this.res);
        };

        Runnable responseVoucherCode = () -> {
            NetworkSimulationUtils.delay();
            long currentTimeMillis = System.currentTimeMillis();
            if (this.res == null) {
                this.res = new ResponseVoucherModel();
            }
            this.res.setCode(UUID.randomUUID().toString());
            this.res.setPhoneNumber(phoneNumber);
            this.res.setMessage(MessageResponseUtils.chooseMessage(startTimeMillis, currentTimeMillis));
            this.res.setVoucherResponseStatus(
                MessageResponseUtils.chooseStatus(startTimeMillis, currentTimeMillis));
            log.info("generate voucher code done, response: {}", this.res);
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(responseVoucherCode);
        executorService.submit(responseTimeout);
        executorService.shutdown();
    }

}
