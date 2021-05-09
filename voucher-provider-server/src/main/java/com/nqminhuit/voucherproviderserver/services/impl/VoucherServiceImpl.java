package com.nqminhuit.voucherproviderserver.services.impl;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import com.nqminhuit.voucherproviderserver.services.VoucherService;
import com.nqminhuit.voucherproviderserver.utils.NetworkSimulationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class VoucherServiceImpl implements VoucherService {

    private static final Logger log = LoggerFactory.getLogger(VoucherServiceImpl.class);

    @Override
    public Mono<String> generateVoucherCode() {
        log.info("generate voucher code ...");

        CompletableFuture<String> cf =
            CompletableFuture.supplyAsync(() -> {
                String code = UUID.randomUUID().toString();
                log.info("should return code: {}", code);
                NetworkSimulationUtils.delay();
                log.info("traffic cleared, returning code {}", code);
                return "###code### " + code;
            })
            .completeOnTimeout("the request is being processed within 30 seconds", 2, TimeUnit.SECONDS);

        return Mono.fromFuture(cf);
    }

}
