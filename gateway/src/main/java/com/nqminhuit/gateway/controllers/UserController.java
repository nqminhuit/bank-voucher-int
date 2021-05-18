package com.nqminhuit.gateway.controllers;

import com.nqminhuit.gateway.controllers.models.UserRequestModel;
import com.nqminhuit.gateway.domain.dtos.BankUserDto;
import com.nqminhuit.gateway.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    String signUpUser(@Validated @RequestBody UserRequestModel body) {
        log.info("Received request to sign up user with data: {}", body);
        userService.createUser(new BankUserDto(body.getUsername(), body.getPassword(), body.getPhoneNumber()));
        return "received signup request with data: " + body;
    }

}
