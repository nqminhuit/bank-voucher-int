package com.nqminhuit.gateway.controllers;

import javax.validation.Valid;
import com.nqminhuit.gateway.controllers.models.AuthResponseModel;
import com.nqminhuit.gateway.controllers.models.UserSignInRequestModel;
import com.nqminhuit.gateway.controllers.models.UserSignUpRequestModel;
import com.nqminhuit.gateway.domain.dtos.BankUserDto;
import com.nqminhuit.gateway.filters.services.GatewayAuthentication;
import com.nqminhuit.gateway.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    AuthResponseModel signUpUser(@Valid @RequestBody UserSignUpRequestModel body) {
        log.info("Received request to sign up user with data: {}", body);
        BankUserDto dto = convert(body);
        dto.setPhoneNumber(body.getPhoneNumber());
        try {
            userService.createUser(dto);
        }
        catch (DataIntegrityViolationException e) {
            return new AuthResponseModel("This username has already existed!", false);
        }
        String jwt = GatewayAuthentication.generateJwt(dto.getUsername(), dto.getPhoneNumber());
        return new AuthResponseModel("Account created!", true, jwt);
    }

    @PostMapping("/sign-in")
    AuthResponseModel signInUser(@Valid @RequestBody UserSignInRequestModel body) {
        log.info("Received request to sign in user with data: {}", body);
        return userService.authenticate(convert(body));
    }

    private BankUserDto convert(UserSignInRequestModel body) {
        return new BankUserDto(body.getUsername(), body.getPassword());
    }

}
