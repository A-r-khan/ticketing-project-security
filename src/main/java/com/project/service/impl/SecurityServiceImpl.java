package com.project.service.impl;

import com.project.entity.User;
import com.project.entity.common.UserPrincipal;
import com.project.repository.UserRepository;
import com.project.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class SecurityServiceImpl  implements SecurityService {


    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);

        if(user==null){
            throw  new UsernameNotFoundException("This user does not exists");
        }

        return new UserPrincipal(user);
    }
}
