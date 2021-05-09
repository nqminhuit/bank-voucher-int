package com.nqminhuit.voucherproviderserver.routes;

import com.nqminhuit.voucherproviderserver.handlers.VoucherHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class VoucherRequestRouter {

    @Bean
    public RouterFunction<ServerResponse> route(VoucherHandler voucherService) {
        return RouterFunctions.route(AppRouters.POST_REQUEST_VOUCHER, voucherService::generateVoucherCode);
    }

}
