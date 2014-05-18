package com.pucilowski.exchange.main.persistence.repository;

import com.pucilowski.exchange.main.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by martin on 31/01/14.
 */


public interface UserRepository extends CrudRepository<User,Integer> {

}
