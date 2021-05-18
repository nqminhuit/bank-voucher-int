package com.nqminhuit.gateway.domain.dtos;

public class BankUserDto {

    private Long id;

    private String username;

    private String password;

    private String phoneNumber;

    public BankUserDto() {};

    public BankUserDto(String username, String password, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "\"UserDto\": {\"id\": \"" + id + "\", \"password\": \"" + password + "\", \"phoneNumber\": \""
            + phoneNumber + "\", \"username\": \"" + username + "\"}";
    }

}
