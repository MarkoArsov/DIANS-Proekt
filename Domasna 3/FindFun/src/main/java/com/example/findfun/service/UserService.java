package com.example.findfun.service;

import com.example.findfun.model.Role;
import com.example.findfun.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String email, Role role);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    User update(User user);
}
