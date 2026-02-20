package com.example.order_service.controller;

import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.FeatureFlagService;
import com.example.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final FeatureFlagService featureFlagService;

    public OrderController(OrderService orderService, OrderRepository orderRepository, FeatureFlagService featureFlagService) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.featureFlagService = featureFlagService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Order> checkout(@RequestBody CheckoutRequest request) {
        int orderQuantity = 0;
        for (OrderController.CheckoutItem item : request.items()) orderQuantity += item.quantity;

        if (featureFlagService.isBulkDiscountEnabled() && orderQuantity > 5) {
            request = new CheckoutRequest(request.items, request.cost * 0.85);
        }

        Order order = orderService.createOrder(request);

        if (featureFlagService.isOrderNotificationsEnabled()) {
            System.out.printf("Notification: Order %d created for product %s total %f\n", order.getId(), order.getItemsJson(), order.getTotalAmount());
        }

        return ResponseEntity.ok(order);
    }

    public record CheckoutRequest(List<CheckoutItem> items, double cost) {}
    public record CheckoutItem(int id, String name, int quantity, double price) {}
}
