package com.serg.repository;

import com.serg.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByOrderByIdAsc();

    Set<Role> findByIdIn(Collection<Long> ids);

    Optional<Role> findByName(String name);
}
