package com.nqminhuit.gateway.repositories;

import com.nqminhuit.gateway.domain.BankUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<BankUser, Long> {

    BankUser findByUsername(String username);

}
