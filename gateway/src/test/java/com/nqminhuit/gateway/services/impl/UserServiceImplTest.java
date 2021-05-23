package com.nqminhuit.gateway.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import com.nqminhuit.gateway.domain.BankUser;
import com.nqminhuit.gateway.domain.dtos.BankUserDto;
import com.nqminhuit.gateway.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.ArgumentCaptor;

@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceImplTest {

    private UserRepository userRepository;

    private UserServiceImpl userService;

    @BeforeAll
    public void setup() {
        userRepository = spy(UserRepository.class);
        doReturn(null).when(userRepository).save(any(BankUser.class));
        doReturn(new BankUser(1L, "batman", hashPassword("batpass"), "+0909123456"))
            .when(userRepository)
            .findByUsername("batman");

        userService = new UserServiceImpl();
        userService.setUserRepository(userRepository);
    }

    @Test
    public void createUser() {
        BankUserDto user = new BankUserDto("batman", "batpass", "+0909123456");

        userService.createUser(user);

        var bankUserCaptor = ArgumentCaptor.forClass(BankUser.class);
        verify(userRepository).save(bankUserCaptor.capture());

        var userEntity = bankUserCaptor.getValue();
        assertNull(userEntity.getId());
        assertEquals("batman", userEntity.getUsername());
        assertEquals("+0909123456", userEntity.getPhoneNumber());
        assertNotEquals("batpass", userEntity.getPassword());
    }

    @Test
    public void authenticate_OK() {
        BankUserDto userDto = new BankUserDto("batman", "batpass", "+0909123456");

        var result = userService.authenticate(userDto);

        assertNotEquals("", result.getJwt());
        assertEquals("Success!", result.getMessage());
        assertTrue(result.getIsAuthenticated());
    }

    @Test
    public void authenticate_userNotExists() {
        BankUserDto userDto = new BankUserDto("batmanNull", "batpass", "+0909123456");

        var result = userService.authenticate(userDto);

        assertNull(result.getJwt());
        assertEquals("Username does not exits", result.getMessage());
        assertFalse(result.getIsAuthenticated());
    }

    @Test
    public void authenticate_invalidUsernameOrPassword() {
        BankUserDto userDto = new BankUserDto("batman", "batpassNull", "+0909123456");

        var result = userService.authenticate(userDto);

        assertNull(result.getJwt());
        assertEquals("Invalid username or password!", result.getMessage());
        assertFalse(result.getIsAuthenticated());
    }

    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
}
