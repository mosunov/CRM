package com.crm.service;


import com.crm.entity.User;
import com.crm.repository.UserRepository;
import com.crm.security.UserPrincipal;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class UserPrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByLogin(username);
        if(null==user){
            throw new UsernameNotFoundException("Cannot find this user: " + username);
        }
        return new UserPrincipal(user);
    }
}
