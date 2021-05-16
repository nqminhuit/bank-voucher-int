package com.nqminhuit.gateway.services.impl;

import com.nqminhuit.gateway.services.ReturnVoucherCodeToClient;

public class ReturnVoucherCodeViaSms implements ReturnVoucherCodeToClient {

    @Override
    public void returnVoucherCode(String code) {
        System.out.println("[via SMS] Your voucher code is " + code);
    }

}
