package com.example.order_service.data;

import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedDatabase(OrderRepository orderRepository) {
        return args -> {
            if (orderRepository.count() == 0) {
                orderRepository.saveAll(List.of(
                        new Order(LocalDateTime.now(), "Processing", 59.99, "items")
                ));
                System.out.println("Seeded Orders DB");
            }
        };
    }
}