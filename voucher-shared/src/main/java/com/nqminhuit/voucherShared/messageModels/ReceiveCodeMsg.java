package com.nqminhuit.voucherShared.messageModels;

import java.io.Serializable;

public class ReceiveCodeMsg implements Serializable {

    private String phoneNumber;

    private String voucherCode;

    private String status;

    public ReceiveCodeMsg() {}

    public ReceiveCodeMsg(String phoneNumber, String voucherCode, String status) {
        this.phoneNumber = phoneNumber;
        this.voucherCode = voucherCode;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\"ReceiveCodeMsg\": {\"phoneNumber\": \"" + phoneNumber + "\", \"status\": \"" + status
            + "\", \"voucherCode\": \"" + voucherCode + "\"}";
    }

}
