package com.nqminhuit.gateway.services.impl;

import com.nqminhuit.gateway.domain.dtos.BankUserDto;
import com.nqminhuit.gateway.domain.mappers.UserMapper;
import com.nqminhuit.gateway.repositories.UserRepository;
import com.nqminhuit.gateway.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(BankUserDto user) {
        // TODO: encrypt user password first:
        userRepository.save(UserMapper.toEntity(user));
    }

}
