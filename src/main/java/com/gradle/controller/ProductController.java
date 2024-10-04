package com.gradle.controller;

import com.gradle.dto.ProductDTO;
import com.gradle.entity.Product;
import com.gradle.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

/**
 * Controlador para gestionar productos en el inventario automotriz.
 * Proporciona endpoints para CRUD y filtros de búsqueda.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts().stream()
            .map(product -> new ProductDTO(product.getId(), product.getName(), product.getQuantity()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO, @RequestParam String userName) {
        Product product = new Product(null, productDTO.getName(), productDTO.getQuantity(), null, userName, null, null);
        Product createdProduct = productService.createProduct(product, userName);
        ProductDTO createdProductDTO = new ProductDTO(createdProduct.getId(), createdProduct.getName(), createdProduct.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product, @RequestParam String userName) {
        return ResponseEntity.ok(productService.updateProduct(id, product, userName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id, @RequestParam String userName) {
        productService.deleteProduct(id, userName);
        return ResponseEntity.noContent().build();
    }
    
    // Endpoints para manejar los filtros de búsqueda
    @GetMapping("/searchByName")
    public ResponseEntity<List<Product>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }
    @GetMapping("/searchByDate")
    public ResponseEntity<List<Product>> searchByDate(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return ResponseEntity.ok(productService.searchByDate(start, end));
    }
    @GetMapping("/searchByUser")
    public ResponseEntity<List<Product>> searchByUser(@RequestParam String userName) {
        return ResponseEntity.ok(productService.searchByUser(userName));
    }
}
