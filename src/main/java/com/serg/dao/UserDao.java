package com.serg.dao;

import com.serg.model.Role;
import com.serg.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    User getUserByUsername(String username);
    void updateUser(User user);
    List<Role> getAllRoles();
    Set<Role> getRolesByIds(List<Long> ids);
    Role getRoleByName(String name);
    void addRole(Role role);
}
