package com.nqminhuit.voucherproviderserver.routes;

import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;

public class AppRouters {

    public static final RequestPredicate POST_REQUEST_VOUCHER = RequestPredicates.POST("/api/request/voucher");
}
