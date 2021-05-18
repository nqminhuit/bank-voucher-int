package com.nqminhuit.voucherShared.dtos;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import com.nqminhuit.voucherShared.dtos.builders.VoucherDtoBuilder;

public class VoucherDto implements Serializable {

    private Long id;

    @NotBlank
    private String phoneNumber;

    @NotBlank
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
