package com.nqminhuit.voucherShared.dtos.builders;

import com.nqminhuit.voucherShared.dtos.VoucherDto;

public class VoucherDtoBuilder {

    private Long id;

    private String phoneNumber;

    private String voucherCode;

    public Long getId() {
        return id;
    }

    public VoucherDtoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public VoucherDtoBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public VoucherDtoBuilder voucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
        return this;
    }

    public VoucherDto build() {
        VoucherDto dto = new VoucherDto();
        dto.setId(this.id);
        dto.setPhoneNumber(this.phoneNumber);
        dto.setVoucherCode(this.voucherCode);
        return dto;
    }

}
