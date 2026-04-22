package com.serg.dao;

import com.serg.model.Role;
import com.serg.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager
                .createQuery("select distinct u from User u left join fetch u.roles order by u.id", User.class)
                .getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User getUserById(Long id) {
        List<User> users = entityManager
                .createQuery("select u from User u left join fetch u.roles where u.id = :id", User.class)
                .setParameter("id", id)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> users = entityManager
                .createQuery("select u from User u left join fetch u.roles where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void updateUser(User user) {
        User existingUser = entityManager.find(User.class, user.getId());
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setSurname(user.getSurname());
            existingUser.setEmail(user.getEmail());
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setRoles(user.getRoles());
        }
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager
                .createQuery("select r from Role r order by r.id", Role.class)
                .getResultList();
    }

    @Override
    public Set<Role> getRolesByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptySet();
        }
        return entityManager
                .createQuery("select r from Role r where r.id in :ids", Role.class)
                .setParameter("ids", ids)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Role getRoleByName(String name) {
        List<Role> roles = entityManager
                .createQuery("select r from Role r where r.name = :name", Role.class)
                .setParameter("name", name)
                .getResultList();
        return roles.isEmpty() ? null : roles.get(0);
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
