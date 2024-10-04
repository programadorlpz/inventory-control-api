package com.gradle.controller;

import com.gradle.dto.ProductDTO;
import com.gradle.entity.Product;
import com.gradle.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        ProductDTO product1 = new ProductDTO(1L, "Filtro de Aceite", 50);
        ProductDTO product2 = new ProductDTO(2L, "Herramientas de reparación", 20);
        when(productService.getAllProducts()).thenReturn(Arrays.asList(
            new Product(1L, "Filtro de Aceite", 50, null, "Juan", null, null),
            new Product(2L, "Herramientas de reparación", 20, null, "Juan", null, null)
        ));

        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();
        List<ProductDTO> products = response.getBody();

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Filtro de Aceite", products.get(0).getName());
        verify(productService, times(1)).getAllProducts();
    }
}
