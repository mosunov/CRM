package com.crm.repository;

import com.crm.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
    User findUserByLogin(String login);
}
