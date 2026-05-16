package com.ecommerce.product_service.service;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.exception.ProductDoesNotExistException;
import com.ecommerce.product_service.exception.UnauthorizedProductAccessException;
import com.ecommerce.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public Product registerProduct(Product product , String sellerId) {

        product.setSellerId(sellerId);

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product getProductById(String id) {

        return productRepository.findById(id).orElseThrow(() ->
                new ProductDoesNotExistException("Product Does Not Exist")
        );

    }

    public List<Product> getProductBySeller(String sellerId) {

        return productRepository.findBySellerId(sellerId);
    }


    public void deleteProduct(String productId , String sellerId){

        Product product = productRepository.findById(productId)
                            .orElseThrow(
                                    () -> new ProductDoesNotExistException("Product Does Not Exist"));

        if (!product.getSellerId().equals(sellerId)){
            throw new UnauthorizedProductAccessException("User does not own this product");
        }



         productRepository.deleteById(productId);
    }

    public Product updateProduct(String productId , String sellerId, Product newProduct){

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        if (!product.getSellerId().equals(sellerId)){
            throw new UnauthorizedProductAccessException("User does not own this product");
        }



        product.setDescription(newProduct.getDescription());
        product.setStockQuantity(newProduct.getStockQuantity());
        product.setPrice(newProduct.getPrice());

        return productRepository.save(product);





    }

}
