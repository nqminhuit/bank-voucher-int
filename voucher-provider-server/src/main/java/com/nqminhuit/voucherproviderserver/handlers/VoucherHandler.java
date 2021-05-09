package com.nqminhuit.voucherproviderserver.handlers;

import com.nqminhuit.voucherproviderserver.utils.NetworkSimulationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class VoucherHandler {

    private static Logger log = LoggerFactory.getLogger(VoucherHandler.class);

    /**
     * this method should only takes up to 3 seconds to react to the client
     */
    public Mono<ServerResponse> generateVoucherCode(ServerRequest request) {
        log.info("generate voucher code ...");
        NetworkSimulationUtils.delay();
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
            .body(BodyInserters.fromValue("ffffffffff"));
    }

}
