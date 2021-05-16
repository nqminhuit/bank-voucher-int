package com.nqminhuit.gateway.services;

public interface ReturnVoucherCodeToClient {

    void returnVoucherCode(String code, String callbackUrl);

}
