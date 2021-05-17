package com.nqminhuit.voucherservice.mappers;

import com.nqminhuit.voucherShared.dtos.VoucherDto;
import com.nqminhuit.voucherservice.domain.Voucher;
import org.mapstruct.Mapper;

@Mapper
public interface VoucherMapper {

    VoucherDto toDto(Voucher voucher);

    Voucher toEntity(VoucherDto voucherDto);

}
