package com.nqminhuit.gateway.controllers.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class UserRequestModel {

    @JsonProperty
    @NonNull
    private String username;

    @JsonProperty
    @NonNull
    private String phoneNumber;

    @JsonProperty
    @NonNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "\"UserRequestModel\": {\"password\": \"" + password + "\", \"phoneNumber\": \"" + phoneNumber
            + "\", \"username\": \"" + username + "\"}";
    }

}
