package com.turkcell.ecommerce.repository.concretes;

import com.turkcell.ecommerce.entities.concretes.Product;
import com.turkcell.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private List<Product> productList;

    public InMemoryProductRepository() {
        productList = new ArrayList<>();
        productList.add(new Product(1L, "Ürün 1", 10, 20.0, "Ürün 1 Açıklama"));
        productList.add(new Product(2L, "Ürün 2", 20, 30.0, "Ürün 2 Açıklama"));
        productList.add(new Product(3L, "Ürün 3", 30, 40.0, "Ürün 3 Açıklama"));
        productList.add(new Product(4L, "Ürün 4", 40, 50.0, "Ürün 4 Açıklama"));
        productList.add(new Product(5L, "Ürün 5", 50, 60.0, "Ürün 5 Açıklama"));
    }

    @Override
    public Product save(Product product) {
        productList.add(product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productList.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void deleteById(Long id) {
        productList.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public void update(Product product) {
        productList.replaceAll(p -> p.getId().equals(product.getId()) ? product : p);
    }

}