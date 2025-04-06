package com.arpico.product.controller;

import com.arpico.product.model.Product;
import com.arpico.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = service.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getAProduct(@PathVariable int id) {
        Optional<Product> product = service.getASingleProduct(id);
        System.out.println("Inside getAProduct" + id);
        if(product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<Product> addANewProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.createANewProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductDetails(@PathVariable int id, @RequestBody Product product) {
        Product updatedProduct = service.updateProductDetailsById(id, product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> removeAProduct(@PathVariable int id) {
        service.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
