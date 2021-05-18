package com.nqminhuit.gateway.controllers.models;

import java.io.Serializable;

public class AuthResponseModel implements Serializable {

    private String message;

    private Boolean isAuthenticated;

    public AuthResponseModel(String message, Boolean isAuthenticated) {
        this.message = message;
        this.isAuthenticated = isAuthenticated;
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

}
