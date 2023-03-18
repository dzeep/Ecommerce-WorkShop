package com.turkcell.ecommerce.repository.abstracts;

import com.turkcell.ecommerce.entities.concretes.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    public Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
    void update(Product product);
}
