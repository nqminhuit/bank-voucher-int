package com.nqminhuit.gateway.services.impl;

import com.nqminhuit.gateway.services.ReturnVoucherCodeToClient;

public class ReturnVoucherCodeViaWeb implements ReturnVoucherCodeToClient {

    @Override
    public void returnVoucherCode(String code) {
        System.out.println("[via WEB] Your voucher code is " + code);
    }

}
