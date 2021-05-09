package com.nqminhuit.voucherservice.services;

import java.util.List;
import com.nqminhuit.voucherservice.dtos.VoucherDto;

public interface VoucherService {

    List<VoucherDto> findAllVouchersByPhoneNumber(String phoneNumber);

    void insertNewVoucher(VoucherDto voucherDto);
}
