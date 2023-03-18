package com.turkcell.ecommerce.business.concretes;

import com.turkcell.ecommerce.business.abstracts.ProductService;
import com.turkcell.ecommerce.entities.concretes.Product;
import com.turkcell.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product addProduct(Product product) {
        if (validateProduct(product)) {
            productRepository.save(product);
        } else {
            throw new IllegalArgumentException("Invalid product");
        }
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if (validateProduct(product)) {
            Product existingProduct = getProductById(id);
            if (existingProduct != null) {
                existingProduct.setName(product.getName());
                existingProduct.setQuantity(product.getQuantity());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setDescription(product.getDescription());
                productRepository.save(existingProduct);
            } else {
                throw new IllegalArgumentException("Product not found");
            }
        } else {
            throw new IllegalArgumentException("Invalid product");
        }
        return product;
    }

    @Override
    public boolean validateProduct(Product product) {
        if (product.getPrice() <= 0) {
            return false;
        }
        if (product.getQuantity() < 0) {
            return false;
        }
        if (product.getDescription().length() < 10 || product.getDescription().length() > 50) {
            return false;
        }
        return true;
    }
}

    