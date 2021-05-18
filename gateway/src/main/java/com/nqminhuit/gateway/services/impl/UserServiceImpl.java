package com.nqminhuit.gateway.services.impl;

import com.nqminhuit.gateway.controllers.models.AuthResponseModel;
import com.nqminhuit.gateway.domain.BankUser;
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

    @Override
    public AuthResponseModel authenticate(BankUserDto userDto) {
        BankUser user = userRepository.findByUsername(userDto.getUsername());
        if (user == null) {
            return new AuthResponseModel("Username does not exits", false);
        }
        if (BCrypt.checkpw(userDto.getPassword(), user.getPassword())) {
            return new AuthResponseModel("Success!", true);
        }
        return new AuthResponseModel("Invalid username or password!", false);
    }

}
