package com.devsam.springbootsecurity.Security;

import org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/dashboard").authenticated()
                .antMatchers("/home").permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }
}