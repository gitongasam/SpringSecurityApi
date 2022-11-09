package com.devsam.springbootsecurity.Security;

import com.devsam.springbootsecurity.Entity.UserEntity;
import com.devsam.springbootsecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email= authentication.getName();
        String password= authentication.getCredentials().toString();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("user not found"));
        if (passwordEncoder.matches(password,user.getPassword())){
            return  new UsernamePasswordAuthenticationToken(email,password,new ArrayList<>());
        }else {
            throw new BadCredentialsException("invalid credentials") ;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
