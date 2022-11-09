package com.devsam.springbootsecurity.Controller;

import com.devsam.springbootsecurity.Entity.UserEntity;
import com.devsam.springbootsecurity.Entity.UserModels;
import com.devsam.springbootsecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Security;

@RestController

public class HomeController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public UserEntity register(@RequestBody UserModels userModels){
      UserEntity user= new UserEntity();
      user.setEmail(userModels.getEmail());
      user.setName(user.getName());
      user.setPassword(passwordEncoder.encode(userModels.getPassword()));
      return userRepository.save(user);
    }

//    LOGIN CODE
    @PostMapping("/login")
    public ResponseEntity<HttpStatus>login(@RequestBody UserModels userModel) throws Exception {
        Authentication authentication;
        try{
            authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userModel.getPassword(),userModel.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);;
        }catch (BadCredentialsException e){
            throw new Exception("invalid credential");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/dashboard")
    public String dashboard(){
        return "you are seeing the dashboard contents";
    }
}
