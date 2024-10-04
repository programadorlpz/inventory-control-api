package com.gradle.service;

import com.gradle.entity.Product;
import com.gradle.exception.ResourceNotFoundException;
import com.gradle.repository.ProductRepository;
import com.gradle.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplement implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // Inyección de dependencias mediante constructor
    public ProductServiceImplement(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado."));
    }

    @Override
    public Product createProduct(Product product, String userName) {
        // Verificamos si el usuario está registrado
        if (!userRepository.existsByName(userName)) {
            throw new IllegalArgumentException("El usuario no está registrado.");
        }

        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("El producto ya existe.");
        }
        
        // Validación de la fecha de ingreso
        if (product.getCreatedAt().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser mayor a la fecha actual.");
        }

        product.setCreatedAt(LocalDate.now());
        product.setCreatedBy(userName);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product, String userName) {
        // Verificamos si el usuario está registrado
        if (!userRepository.existsByName(userName)) {
            throw new IllegalArgumentException("El usuario no está registrado.");
        }

        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setModifiedBy(userName);
        existingProduct.setModifiedAt(LocalDate.now());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id, String userName) {
        // Verificamos si el usuario está registrado
        if (!userRepository.existsByName(userName)) {
            throw new IllegalArgumentException("El usuario no está registrado.");
        }

        Product existingProduct = getProductById(id);
        if (!existingProduct.getCreatedBy().equals(userName)) {
            throw new IllegalArgumentException("Solo el usuario que registró puede eliminar el producto.");
        }
        productRepository.delete(existingProduct);
    }

    // Filtros de búsqueda
    @Override
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> searchByDate(LocalDate startDate, LocalDate endDate) {
        return productRepository.findByCreatedAtBetween(startDate, endDate);
    }

    @Override
    public List<Product> searchByUser(String userName) {
        return productRepository.findByCreatedBy(userName);
    }
}
