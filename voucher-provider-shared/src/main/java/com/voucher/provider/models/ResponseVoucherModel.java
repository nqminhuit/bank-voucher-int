package com.voucher.provider.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.voucher.provider.models.builders.ResponseVoucherModelBuilder;
import com.voucher.provider.models.enumerations.VoucherResponseStatus;

public class ResponseVoucherModel implements Serializable {

    @JsonProperty
    private String message;

    @JsonProperty
    private String code;

    @JsonProperty
    private String phoneNumber;

    @JsonProperty
    private String codeVerifier;

    @JsonProperty
    private String transformMethod;

    @JsonProperty
    private VoucherResponseStatus voucherResponseStatus;

    public static ResponseVoucherModelBuilder builder() {
        return new ResponseVoucherModelBuilder();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCodeVerifier() {
        return codeVerifier;
    }

    public void setCodeVerifier(String codeVerifier) {
        this.codeVerifier = codeVerifier;
    }

    public String getTransformMethod() {
        return transformMethod;
    }

    public void setTransformMethod(String transformMethod) {
        this.transformMethod = transformMethod;
    }

    public VoucherResponseStatus getVoucherResponseStatus() {
        return voucherResponseStatus;
    }

    public void setVoucherResponseStatus(VoucherResponseStatus voucherResponseStatus) {
        this.voucherResponseStatus = voucherResponseStatus;
    }

    @Override
    public String toString() {
        return "\"ResponseVoucherModel\": {\"code\": \"" + code + "\", \"codeVerifier\": \"" + codeVerifier
            + "\", \"message\": \"" + message + "\", \"phoneNumber\": \"" + phoneNumber
            + "\", \"transformMethod\": \"" + transformMethod + "\", \"voucherResponseStatus\": \""
            + voucherResponseStatus + "\"}";
    }

    public String toJson() {
        return "{\"code\": \"" + code + "\", \"codeVerifier\": \"" + codeVerifier
            + "\", \"message\": \"" + message + "\", \"phoneNumber\": \"" + phoneNumber
            + "\", \"transformMethod\": \"" + transformMethod + "\", \"voucherResponseStatus\": \""
            + voucherResponseStatus + "\"}";
    }

}
