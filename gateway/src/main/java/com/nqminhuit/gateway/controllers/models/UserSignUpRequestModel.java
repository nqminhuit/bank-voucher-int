package com.nqminhuit.gateway.controllers.models;

import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSignUpRequestModel extends UserSignInRequestModel {

    @JsonProperty
    @NotBlank
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "\"UserSignUpRequestModel\": {\"phoneNumber\": \"" + phoneNumber + "\", " +
            super.toString() + "}";
    }


}
