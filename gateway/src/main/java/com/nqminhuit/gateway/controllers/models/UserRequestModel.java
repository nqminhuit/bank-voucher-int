package com.nqminhuit.gateway.controllers.models;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequestModel implements Serializable {

    @JsonProperty
    @NotBlank
    private String username;

    @JsonProperty
    @NotBlank
    private String phoneNumber;

    @JsonProperty
    @NotBlank
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
