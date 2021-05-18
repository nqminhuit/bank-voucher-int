package com.nqminhuit.gateway.domain.mappers;

import com.nqminhuit.gateway.domain.User;
import com.nqminhuit.gateway.domain.dtos.UserDto;

public class UserMapper {

    public User toEntity(UserDto dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getPhoneNumber());
    }

}
