package com.ecommerce.product_service.controller;


import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {

        this.productService = productService;

    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {

        return productService.getProductById(id);

    }

    @GetMapping
    public List<Product> getAllProduct() {

        return productService.getAllProducts();

    }

    @GetMapping("/seller/{id}")
    public List<Product> getProductsBySeller(@PathVariable String id) {

        return productService.getProductBySeller(id);

    }

    @PostMapping("/register")
    public Product registerProduct(@RequestBody Product product) {

        return productService.registerProduct(product);

    }


}
