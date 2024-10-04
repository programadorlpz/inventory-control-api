package com.gradle.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gradle.entity.Product;
import com.gradle.repository.ProductRepository;
import com.gradle.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImplement productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProductSuccess() {
        Product product = new Product(null, "Filtro de Aceite", 50, LocalDate.now(), "Juan", null, null);
        when(userRepository.existsByName("Juan")).thenReturn(true);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.createProduct(product, "Juan");

        assertNotNull(result);
        assertEquals("Filtro de Aceite", result.getName());
        assertEquals(50, result.getQuantity());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testCreateProductFailureUserNotFound() {
        Product product = new Product(null, "Filtro de Aceite", 50, LocalDate.now(), "Carlos", null, null);
        when(userRepository.existsByName("Carlos")).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.createProduct(product, "Carlos");
        });

        assertEquals("El usuario no está registrado.", exception.getMessage());
    }
}
