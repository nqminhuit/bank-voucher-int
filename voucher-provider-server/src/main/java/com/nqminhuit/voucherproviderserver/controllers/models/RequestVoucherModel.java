package com.nqminhuit.voucherproviderserver.controllers.models;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestVoucherModel implements Serializable {

    @NotBlank
    @JsonProperty
    private String phoneNumber;

    @NotBlank
    @JsonProperty
    private String callbackUrl;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    @Override
    public String toString() {
        return "\"RequestVoucherModel\": {\"callbackUrl\": \"" + callbackUrl + "\", \"phoneNumber\": \""
            + phoneNumber + "\"}";
    }

}
