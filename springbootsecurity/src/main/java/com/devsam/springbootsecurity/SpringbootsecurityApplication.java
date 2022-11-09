package com.devsam.springbootsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringbootsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootsecurityApplication.class, args);
	}

}
