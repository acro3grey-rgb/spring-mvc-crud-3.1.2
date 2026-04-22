package com.serg.service;

import com.serg.dao.UserDao;
import com.serg.model.Role;
import com.serg.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles() {
        return userDao.getAllRoles();
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Role> getRolesByIds(List<Long> ids) {
        return userDao.getRolesByIds(ids);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleByName(String name) {
        return userDao.getRoleByName(name);
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        userDao.addRole(role);
    }
}
