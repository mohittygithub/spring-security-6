package com.tyagi.springsecurity6.repository;

import com.tyagi.springsecurity6.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query(value="SELECT * FROM Role WHERE name = ?1", nativeQuery = true)
    Role findByName(String name);
}
