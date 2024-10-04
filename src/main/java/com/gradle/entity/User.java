package com.gradle.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entidad que representa a un usuario en el sistema.
 * Cada usuario tiene un nombre, edad, puesto y la fecha en que se unió.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String position;

    @Column(name = "joined_at", nullable = false)
    private LocalDate joinedAt;

    // Constructor por defecto
    public User() {
    }

    // Constructor completo
    public User(Long id, String name, int age, String position, LocalDate joinedAt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
        this.joinedAt = joinedAt;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDate joinedAt) {
        this.joinedAt = joinedAt;
    }
}
