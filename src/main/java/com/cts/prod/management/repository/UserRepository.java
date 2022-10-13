package com.cts.prod.management.repository;
import org.springframework.data.repository.CrudRepository;

import com.cts.prod.management.entity.UserDao;

public interface UserRepository extends CrudRepository<UserDao, Integer> {
    UserDao findByUserName(String userName);
}