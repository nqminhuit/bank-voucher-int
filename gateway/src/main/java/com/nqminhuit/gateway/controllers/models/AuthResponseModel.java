package com.nqminhuit.gateway.controllers.models;

import java.io.Serializable;

public class AuthResponseModel implements Serializable {

    private String message;

    private Boolean isAuthenticated;

    private String jwt;

    public AuthResponseModel(String message, Boolean isAuthenticated) {
        this.message = message;
        this.isAuthenticated = isAuthenticated;
    }

    public AuthResponseModel(String message, Boolean isAuthenticated, String jwt) {
        this.message = message;
        this.isAuthenticated = isAuthenticated;
        this.jwt = jwt;
    }

    public AuthResponseModel() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
