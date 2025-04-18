package com.arpico.order.service;

import com.arpico.order.model.Order;
import com.arpico.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    private final WebClient webClient;

    @Autowired
    OrderRepository repository;

    public OrderService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Order> getAllOrders() {
        List<Order> allOrders = repository.findAll();
        return allOrders;
    }

    public Optional<Order> getASingleOrder(int orderId) {
        Optional<Order> order = repository.findById(orderId);
        return order;
    }

//    public Order createANewOrder(Order order) {
//         return repository.save(order);
//    }

    public Order updateOrderDetailsById(int orderId, Order order) {
        Optional<Order> existingOrder = repository.findById(orderId);
        if(existingOrder.isPresent()) {
            Order existingOrderDetails = existingOrder.get();
            existingOrderDetails.setOrderDate(order.getOrderDate());
            existingOrderDetails.setStatus(order.getStatus());
            existingOrderDetails.setQuantity(order.getQuantity());
            existingOrderDetails.setProductId(order.getProductId());
            existingOrderDetails.setTotalPrice(order.getTotalPrice());

            return repository.save(existingOrderDetails);
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }

    public void deleteOrderById(int orderId) {
        Optional<Order> existingOrder = repository.findById(orderId);
        if(existingOrder.isPresent()) {
            repository.deleteById(orderId);
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }

}
