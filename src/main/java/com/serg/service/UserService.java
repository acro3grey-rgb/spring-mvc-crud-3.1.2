package com.serg.service;

import com.serg.model.Role;
import com.serg.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    void updateUser(User user);
    List<Role> getAllRoles();
    Set<Role> getRolesByIds(List<Long> ids);
    Role getRoleByName(String name);
    void addRole(Role role);
}
