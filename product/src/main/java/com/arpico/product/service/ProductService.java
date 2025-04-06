package com.arpico.product.service;

import com.arpico.product.model.Product;
import com.arpico.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public List<Product> getAllProducts() {
        List<Product> allProducts = repository.findAll();
        return allProducts;
    }

    public Optional<Product> getASingleProduct(int id) {
        System.out.println("Inside getASingleProduct" + id);
        Optional<Product> product = repository.findById(id);
        return product;
    }

    public Product createANewProduct(Product product) {
         return repository.save(product);
    }

    public Product updateProductDetailsById(int id, Product product) {
        Optional<Product> existingProduct = repository.findById(id);
        if(existingProduct.isPresent()) {
            Product existingProductDetails = existingProduct.get();
            existingProductDetails.setProductName(product.getProductName());
            existingProductDetails.setDescription(product.getDescription());
            existingProductDetails.setForSale(product.getForSale());

            return repository.save(existingProductDetails);
        } else {
            throw new RuntimeException("Product not found with ID: " + id);
        }
    }

    public void deleteProductById(int id) {
        Optional<Product> existingProduct = repository.findById(id);
        if(existingProduct.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with ID: " + id);
        }
    }

}
