package com.nqminhuit.gateway.services;

import com.nqminhuit.gateway.domain.dtos.BankUserDto;

public interface UserService {

    void createUser(BankUserDto user);

}
