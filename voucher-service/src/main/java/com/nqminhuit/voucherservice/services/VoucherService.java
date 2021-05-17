package com.nqminhuit.voucherservice.services;

import java.util.List;
import com.nqminhuit.voucherShared.dtos.VoucherDto;

public interface VoucherService {

    List<VoucherDto> findAllVouchersByPhoneNumber(String phoneNumber);

    void insertNewVoucher(VoucherDto voucherDto);
}
