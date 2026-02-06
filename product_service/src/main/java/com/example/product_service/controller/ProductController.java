package com.example.product_service.controller;

import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    ProductRepository mySqlRepository;

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return mySqlRepository.findAll();
    }

    @GetMapping("/{index}")
    public Product getProduct(@PathVariable int index) {
        return mySqlRepository.findById(index)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @PostMapping
    public Product addProduct(@RequestBody Product newProduct) {
        return mySqlRepository.save(newProduct);
    }

    @PutMapping("/{index}")
    public Product updateProduct(@PathVariable int index, @RequestBody Product updatedProduct) {
        Product product = mySqlRepository.findById(index)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Product.setProductDetails(updatedProduct, product);
        return mySqlRepository.save(product);
    }

    @DeleteMapping("/{index}")
    public Product deleteProduct(@PathVariable int index) {
        Product product = getProduct(index);
        mySqlRepository.delete(product);
        return product;
    }
}
