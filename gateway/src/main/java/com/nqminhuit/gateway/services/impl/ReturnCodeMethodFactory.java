package com.nqminhuit.gateway.services.impl;

import com.nqminhuit.gateway.services.ReturnVoucherCodeToClient;
import com.voucher.provider.models.enumerations.VoucherResponseStatus;

public class ReturnCodeMethodFactory {

    public static ReturnVoucherCodeToClient getMethod(String codeStatus) {
        if (VoucherResponseStatus.LATE_SUCCESS.name().equals(codeStatus)) {
            return new ReturnVoucherCodeViaSms();
        }
        return new ReturnVoucherCodeViaWeb();
    }

}
