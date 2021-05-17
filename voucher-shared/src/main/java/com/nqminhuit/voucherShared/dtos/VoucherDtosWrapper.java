package com.nqminhuit.voucherShared.dtos;

import java.util.List;

public class VoucherDtosWrapper {

    private List<VoucherDto> voucherDtos;

    public VoucherDtosWrapper() {}

    public VoucherDtosWrapper(List<VoucherDto> voucherDtos) {
        this.voucherDtos = voucherDtos;
    }

    public List<VoucherDto> getVoucherDtos() {
        return voucherDtos;
    }

    public void setVoucherDtos(List<VoucherDto> voucherDtos) {
        this.voucherDtos = voucherDtos;
    }

}
