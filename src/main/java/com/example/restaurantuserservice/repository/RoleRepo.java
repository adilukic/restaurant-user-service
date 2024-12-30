package com.example.restaurantuserservice.repository;

import com.example.restaurantuserservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByName(String name);
}
