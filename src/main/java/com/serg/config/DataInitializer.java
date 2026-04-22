package com.serg.config;

import com.serg.model.Role;
import com.serg.model.User;
import com.serg.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserService userService, PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = userService.getRoleByName("ROLE_ADMIN");
            if (adminRole == null) {
                adminRole = new Role("ROLE_ADMIN");
                userService.addRole(adminRole);
            }

            Role userRole = userService.getRoleByName("ROLE_USER");
            if (userRole == null) {
                userRole = new Role("ROLE_USER");
                userService.addRole(userRole);
            }

            if (!userExists(userService, "admin")) {
                userService.addUser(new User(
                        "Admin",
                        "Adminov",
                        "admin@example.com",
                        "admin",
                        passwordEncoder.encode("admin"),
                        Set.of(adminRole, userRole)
                ));
            }

            if (!userExists(userService, "user")) {
                userService.addUser(new User(
                        "User",
                        "Userov",
                        "user@example.com",
                        "user",
                        passwordEncoder.encode("user"),
                        Set.of(userRole)
                ));
            }
        };
    }

    private boolean userExists(UserService userService, String username) {
        try {
            userService.loadUserByUsername(username);
            return true;
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }
}
