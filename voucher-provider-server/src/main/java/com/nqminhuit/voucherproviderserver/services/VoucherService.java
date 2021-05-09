package com.nqminhuit.voucherproviderserver.services;

import reactor.core.publisher.Mono;

public interface VoucherService {

    Mono<String> generateVoucherCode();

}
