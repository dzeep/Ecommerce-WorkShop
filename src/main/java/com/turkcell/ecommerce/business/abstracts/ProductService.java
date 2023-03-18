package com.turkcell.ecommerce.business.abstracts;

import com.turkcell.ecommerce.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product addProduct(Product product);
    void deleteProduct(Long id);
    Product updateProduct(Long id, Product product);
    boolean validateProduct(Product product);

}
