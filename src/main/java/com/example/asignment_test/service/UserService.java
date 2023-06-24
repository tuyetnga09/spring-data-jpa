package com.example.asignment_test.service;

import com.example.asignment_test.dto.UserRegistrationDto;
import com.example.asignment_test.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

 User save(UserRegistrationDto registrationDto);
}
