package com.nqminhuit.voucherservice.mappers;

import com.nqminhuit.voucherservice.domain.Voucher;
import com.nqminhuit.voucherservice.domain.dtos.VoucherDto;
import org.mapstruct.Mapper;

@Mapper
public interface VoucherMapper {

    VoucherDto toDto(Voucher voucher);

    Voucher toEntity(VoucherDto voucherDto);

}
