package com.nqminhuit.gateway.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import com.nqminhuit.gateway.controllers.models.UserSignInRequestModel;
import com.nqminhuit.gateway.controllers.models.UserSignUpRequestModel;
import com.nqminhuit.gateway.domain.dtos.BankUserDto;
import com.nqminhuit.gateway.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class UserControllerTest {

    private UserController userController;

    private UserService userService = spy(UserService.class);

    @BeforeEach
    public void setup() {
        userController = new UserController();
        userController.setUserService(userService);
    }

    @Test
    public void signInUser() {
        UserSignInRequestModel body = new UserSignInRequestModel();
        body.setPassword("password321");
        body.setUsername("username123");

        userController.signInUser(body);

        var bankUserDtoCaptor = ArgumentCaptor.forClass(BankUserDto.class);
        verify(userService).authenticate(bankUserDtoCaptor.capture());

        var bankUser = bankUserDtoCaptor.getValue();
        assertEquals("username123", bankUser.getUsername());
        assertEquals("password321", bankUser.getPassword());
        assertNull(bankUser.getPhoneNumber());
    }

    @Test
    public void signUpUser() {
        var body = new UserSignUpRequestModel();
        body.setPassword("password321");
        body.setUsername("username123");
        body.setPhoneNumber("0909998877");

        userController.signUpUser(body);

        var bankUserDtoCaptor = ArgumentCaptor.forClass(BankUserDto.class);
        verify(userService).createUser(bankUserDtoCaptor.capture());

        var bankUser = bankUserDtoCaptor.getValue();
        assertEquals("username123", bankUser.getUsername());
        assertEquals("password321", bankUser.getPassword());
        assertEquals("0909998877", bankUser.getPhoneNumber());
    }

    @Test
    public void signUpUser_shouldReturnJwt() {
        var body = new UserSignUpRequestModel();
        body.setPassword("password321");
        body.setUsername("username123");
        body.setPhoneNumber("0909998877");

        var result = userController.signUpUser(body);

        assertTrue(result.getIsAuthenticated());
        assertNotNull(result.getJwt());
        assertFalse(result.getJwt().isBlank());
        assertEquals("Account created!", result.getMessage());
    }
}
