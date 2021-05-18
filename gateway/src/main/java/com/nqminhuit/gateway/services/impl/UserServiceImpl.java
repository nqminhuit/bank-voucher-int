package com.nqminhuit.gateway.services.impl;

import com.nqminhuit.gateway.domain.dtos.UserDto;
import com.nqminhuit.gateway.domain.mappers.UserMapper;
import com.nqminhuit.gateway.repositories.UserRepository;
import com.nqminhuit.gateway.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void createUser(UserDto user) {
        // TODO: encrypt user password first:
        userRepository.save(userMapper.toEntity(user));
    }

}
