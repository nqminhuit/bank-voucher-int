package com.nqminhuit.gateway.rest_clients;

import java.util.List;
import com.nqminhuit.voucherShared.dtos.VoucherDto;

public interface VoucherServiceClient {

    public List<VoucherDto> findAllVouchersByPhoneNumber(String phoneNumber);

}
