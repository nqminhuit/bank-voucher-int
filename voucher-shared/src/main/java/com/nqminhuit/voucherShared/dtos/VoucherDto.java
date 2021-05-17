package com.nqminhuit.voucherShared.dtos;

import com.nqminhuit.voucherShared.dtos.builders.VoucherDtoBuilder;
import org.springframework.lang.NonNull;

public class VoucherDto {

    private Long id;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String voucherCode;

    public static VoucherDtoBuilder builder() {
        return new VoucherDtoBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    @Override
    public String toString() {
        return "\"VoucherDto\": {\"id\": \"" + id + "\", \"phoneNumber\": \"" + phoneNumber
            + "\", \"voucherCode\": \"" + voucherCode + "\"}";
    }

}
