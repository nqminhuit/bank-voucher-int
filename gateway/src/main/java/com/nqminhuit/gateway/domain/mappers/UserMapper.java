package com.nqminhuit.gateway.domain.mappers;

import com.nqminhuit.gateway.domain.BankUser;
import com.nqminhuit.gateway.domain.dtos.BankUserDto;

public class UserMapper {

    public static BankUser toEntity(BankUserDto dto) {
        return new BankUser(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getPhoneNumber());
    }

}
