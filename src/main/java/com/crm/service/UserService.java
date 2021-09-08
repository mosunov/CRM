package com.crm.service;

import com.crm.repository.UserRepository;
import com.crm.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerNewUserAccount(User user) throws Exception {
        if(loginExist(user.getLogin())){
            throw new Exception("This user: "+user.getLogin()+" is already exist");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

    private boolean loginExist(String login) {
        return userRepository.findUserByLogin(login)!=null;
    }
}
