package com.gradle.repository;

import com.gradle.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para acceder a los datos de productos en la base de datos.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    
    // Obtener productos ordenados por fecha de creación de forma descendente
    List<Product> findAllByOrderByCreatedAtDesc();

    // Filtrar por nombre
    List<Product> findByNameContainingIgnoreCase(String name);
    // Filtrar por fecha
    List<Product> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);
    // Filtrar por usuario
    List<Product> findByCreatedBy(String createdBy);
}
