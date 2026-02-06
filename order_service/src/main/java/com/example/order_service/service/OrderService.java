package com.example.order_service.service;

import com.example.order_service.controller.OrderController;
import com.example.order_service.data.ProductDto;
import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public Order createOrder(OrderController.CheckoutRequest request) {
        Order order = new Order();
        order.setTotalAmount(request.cost());

        String productServiceUrl = "http://product-service:8081";
//        String productServiceUrl = "http://localhost:8081";
        List<String> itemNames = new ArrayList<>();

        for (OrderController.CheckoutItem item : request.items()) {
            String productEndpoint = productServiceUrl + "/products/" + item.id();
            ProductDto product = restTemplate.getForObject(productEndpoint, ProductDto.class);

            if (product != null) {
                itemNames.add(item.quantity() + "x " + product.getName());
            } else {
                throw new RuntimeException("Product with ID " + item.id() + " not found");
            }
        }

        try {
            // Convert the list of item descriptions to JSON
            String itemsJson = objectMapper.writeValueAsString(itemNames);
            order.setItemsJson(itemsJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize items", e);
        }

        return orderRepository.save(order);
    }
}
