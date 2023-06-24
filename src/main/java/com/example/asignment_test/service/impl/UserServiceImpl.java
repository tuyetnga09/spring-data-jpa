package com.example.asignment_test.service.impl;

import com.example.asignment_test.dto.UserRegistrationDto;
import com.example.asignment_test.entity.Role;
import com.example.asignment_test.entity.User;
import com.example.asignment_test.repository.UseRepository;
import com.example.asignment_test.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Lazy
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UseRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserServiceImpl(@Lazy UseRepository userRepository,BCryptPasswordEncoder passwordEncoder,@Lazy UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                registrationDto.getPassword(), Arrays.asList(new Role("ROLE_USER")));
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

//    public void createUser() {
//        UserRegistrationDto userDto = new UserRegistrationDto();
//        userDto.setFirstName("Xuan Vu 30");
//        userDto.setLastName("Tran");
//        userDto.setEmail("xuanvu30@gmail.com");
//        userDto.setPassword("123456");
//        User user = userService.save(userDto);
//        Role role = new Role("ROLE_USER");
//
//        user.addRole(role);
//
//        userRepository.save(user);
//    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }


}
