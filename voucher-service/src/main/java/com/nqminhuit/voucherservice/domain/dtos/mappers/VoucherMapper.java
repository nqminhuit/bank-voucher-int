package com.nqminhuit.voucherservice.domain.dtos.mappers;

import com.nqminhuit.voucherservice.domain.Voucher;
import com.nqminhuit.voucherservice.domain.dtos.VoucherDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface VoucherMapper extends Converter<Voucher, VoucherDto> {

    VoucherDto toDto(Voucher voucher);

    Voucher toEntity(VoucherDto voucherDto);

}
