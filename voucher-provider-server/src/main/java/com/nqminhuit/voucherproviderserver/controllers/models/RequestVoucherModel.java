package com.nqminhuit.voucherproviderserver.controllers.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class RequestVoucherModel implements Serializable {

    @NonNull
    @JsonProperty
    private String phoneNumber;

    @NonNull
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
