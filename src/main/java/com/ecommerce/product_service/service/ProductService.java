package com.ecommerce.product_service.service;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public Product registerProduct(Product product) {


        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product getProductById(String id) {

        return productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Product not found")
        );

    }

    public List<Product> getProductBySeller(String sellerId) {

        return productRepository.findBySellerId(sellerId);
    }


}
