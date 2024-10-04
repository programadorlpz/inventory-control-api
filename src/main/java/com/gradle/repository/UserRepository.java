package com.gradle.repository;

import com.gradle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Verificar si un usuario existe por su nombre
    boolean existsByName(String name);
}
