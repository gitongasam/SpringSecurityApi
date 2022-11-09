package com.devsam.springbootsecurity.Security;

import com.devsam.springbootsecurity.Entity.UserEntity;
import com.devsam.springbootsecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
//    validating user against the database
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      UserEntity userEntity= userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("username not found"));
       return  new User(userEntity.getEmail(),userEntity.getPassword(),new ArrayList<>());
    }
}

