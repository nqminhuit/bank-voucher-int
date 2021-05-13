package com.nqminhuit.voucherShared.messageModels;

import java.io.Serializable;

public class ReceiveCodeMsg implements Serializable {

    private String phoneNumber;

    private String voucherCode;

    public ReceiveCodeMsg() {}

    public ReceiveCodeMsg(String phoneNumber, String voucherCode) {
        this.phoneNumber = phoneNumber;
        this.voucherCode = voucherCode;
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
        return "\"ReceiveCodeMsg\": {\"phoneNumber\": \"" + phoneNumber + "\", \"voucherCode\": \""
            + voucherCode + "\"}";
    }
}
