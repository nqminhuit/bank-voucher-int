package com.nqminhuit.gateway.repositories;

import com.nqminhuit.gateway.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
