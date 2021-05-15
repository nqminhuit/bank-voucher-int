package com.nqminhuit.voucherproviderserver.services;

import com.nqminhuit.voucherproviderserver.controllers.models.RequestVoucherModel;
import com.nqminhuit.voucherproviderserver.controllers.models.ResponseVoucherModel;

public interface VoucherService {

    /**
     * This method has up to 3 seconds to return the code, otherwise it has to return via callbackUrl
     */
    ResponseVoucherModel generateVoucherCode(RequestVoucherModel req);

}
