package com.nqminhuit.voucherservice.repositories.mock_impl;

import com.nqminhuit.voucherShared.dtos.VoucherDto;
import com.nqminhuit.voucherservice.domain.Voucher;
import com.nqminhuit.voucherservice.domain.mappers.VoucherMapper;

public class VoucherMapperImpl implements VoucherMapper {

    @Override
    public VoucherDto convert(Voucher source) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VoucherDto toDto(Voucher voucher) {
        return VoucherDto.builder()
            .id(voucher.getId())
            .phoneNumber(voucher.getPhoneNumber())
            .voucherCode(voucher.getVoucherCode())
            .build();
    }

    @Override
    public Voucher toEntity(VoucherDto voucherDto) {
        Voucher entity = new Voucher();
        entity.setId(voucherDto.getId());
        entity.setPhoneNumber(voucherDto.getPhoneNumber());
        entity.setVoucherCode(voucherDto.getVoucherCode());
        return entity;
    }

}
