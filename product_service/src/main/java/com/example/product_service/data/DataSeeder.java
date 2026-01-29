package com.example.product_service.data;

import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedDatabase(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.saveAll(List.of(
                        new Product("Nike Air Max 90", "Classic red and white", 129.99, "SKU-001", "http://localhost:8081/images/nike-shoe-thumbnail.png", 0),
                        new Product("Adidas UltraBoost 22", "Black and white running shoe", 149.99, "SKU-002", "http://localhost:8081/images/nike-shoe-thumbnail.png", 9),
                        new Product("Puma Suede Classic", "Blue suede casual", 89.99, "SKU-003", "http://localhost:8081/images/nike-shoe-thumbnail.png", 34),
                        new Product("Reebok Club C 85", "White leather retro", 99.99, "SKU-004", "http://localhost:8081/images/nike-shoe-thumbnail.png", 0),
                        new Product("New Balance 990v5", "Grey premium running", 179.99, "SKU-005", "http://localhost:8081/images/nike-shoe-thumbnail.png", 10),
                        new Product("Converse Chuck Taylor", "Black high-top", 69.99, "SKU-006", "http://localhost:8081/images/nike-shoe-thumbnail.png", 11),
                        new Product("Vans Old Skool", "Classic black/white skate", 79.99, "SKU-007", "http://localhost:8081/images/nike-shoe-thumbnail.png", 4),
                        new Product("Asics Gel-Kayano 28", "Blue stability running shoe", 159.99, "SKU-008", "http://localhost:8081/images/nike-shoe-thumbnail.png", 1),
                        new Product("Jordan Air 1 Low", "White/red basketball sneaker", 139.99, "SKU-009", "http://localhost:8081/images/nike-shoe-thumbnail.png", 2),
                        new Product("Under Armour HOVR Phantom", "Black performance shoe", 149.99, "SKU-010", "http://localhost:8081/images/nike-shoe-thumbnail.png", 3),
                        new Product("Saucony Jazz Original", "Green retro running", 89.99, "SKU-011", "http://localhost:8081/images/nike-shoe-thumbnail.png", 9),
                        new Product("Brooks Ghost 15", "Grey neutral running", 129.99, "SKU-012", "http://localhost:8081/images/nike-shoe-thumbnail.png", 78)
                ));
                System.out.println("Seeded Shoes DB");
            }
        };
    }
}