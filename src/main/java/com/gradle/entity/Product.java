package com.gradle.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entidad que representa un producto en el sistema de inventario automotriz.
 * Cada producto tiene un nombre único, cantidad y fechas de creación y modificación.
 */
@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "modified_at")
    private LocalDate modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    // Constructor por defecto
    public Product() {
        this.createdAt = LocalDate.now();
    }

    // Constructor completo
    public Product(Long id, String name, int quantity, LocalDate createdAt, String createdBy, LocalDate modifiedAt, String modifiedBy) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    // Getters y Setters completos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDate modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
