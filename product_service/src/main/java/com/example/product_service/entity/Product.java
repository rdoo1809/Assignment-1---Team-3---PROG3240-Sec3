package com.example.product_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String sku;
    private String image;
    private Integer stock;

    public Product() {}

    public Product(String name, String description, Double price,
                   String sku, String image, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.sku = sku;
        this.image = image;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Transient
    public boolean getInStock() {
        return stock != null && stock > 0;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public static void setProductDetails(Product updatedProduct, Product product) {
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setSku(updatedProduct.getSku());
        product.setImage(updatedProduct.getImage());
        product.setStock(updatedProduct.getStock());
    }
}
