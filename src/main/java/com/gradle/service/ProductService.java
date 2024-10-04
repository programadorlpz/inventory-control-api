package com.gradle.service;

import com.gradle.entity.Product;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz de servicio para la lógica de negocio relacionada con productos.
 */
public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product, String userName);
    Product updateProduct(Long id, Product product, String userName);
    void deleteProduct(Long id, String userName);

    // Filtros de búsqueda
    List<Product> searchByName(String name);
    List<Product> searchByDate(LocalDate startDate, LocalDate endDate);
    List<Product> searchByUser(String userName);
}
