package com.example.order_service.service;

import com.example.order_service.controller.OrderController;
import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder(OrderController.CheckoutRequest request) {
        Order order = new Order();
        order.setTotalAmount(request.cost());

        try {
            String itemsJson = objectMapper.writeValueAsString(request.items());
            order.setItemsJson(itemsJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize items", e);
        }

        return orderRepository.save(order);
    }
}
