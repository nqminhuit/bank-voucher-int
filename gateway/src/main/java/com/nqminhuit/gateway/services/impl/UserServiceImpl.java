package com.nqminhuit.gateway.services.impl;

import com.nqminhuit.gateway.domain.dtos.BankUserDto;
import com.nqminhuit.gateway.domain.mappers.UserMapper;
import com.nqminhuit.gateway.repositories.UserRepository;
import com.nqminhuit.gateway.services.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(BankUserDto user) {
        userRepository.save(UserMapper.toEntity(hashPassword(user)));
    }

    private BankUserDto hashPassword(BankUserDto user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        BankUserDto hashedDto = new BankUserDto();
        BeanUtils.copyProperties(user, hashedDto);
        hashedDto.setPassword(hashedPassword);
        return hashedDto;
    }

}
