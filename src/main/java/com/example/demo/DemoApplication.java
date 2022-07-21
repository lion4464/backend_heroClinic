package com.example.demo;

import com.example.demo.role.RoleRequest;
import com.example.demo.role.RoleService;
import com.example.demo.user.DataStatusEnum;
import com.example.demo.user.UserRequest;
import com.example.demo.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.io.UnsupportedEncodingException;

@SpringBootApplication

public class DemoApplication {


	public static void main(String[] args) throws UnsupportedEncodingException {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}
