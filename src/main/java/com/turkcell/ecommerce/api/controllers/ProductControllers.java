package com.turkcell.ecommerce.api.controllers;

import com.turkcell.ecommerce.business.abstracts.ProductService;
import com.turkcell.ecommerce.entities.concretes.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductControllers {

    private final ProductService productService;

    public ProductControllers(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (product.getPrice() <= 0) {
            return ResponseEntity.badRequest().build();
        }

        if (product.getQuantity() < 0) {
            return ResponseEntity.badRequest().build();
        }

        if (product.getDescription().length() < 10 || product.getDescription().length() > 50) {
            return ResponseEntity.badRequest().build();
        }

        Product createdProduct = productService.addProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody Product product) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }

        if (product.getPrice() <= 0) {
            return ResponseEntity.badRequest().build();
        }

        if (product.getQuantity() < 0) {
            return ResponseEntity.badRequest().build();
        }

        if (product.getDescription().length() < 10 || product.getDescription().length() > 50) {
            return ResponseEntity.badRequest().build();
        }

        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
