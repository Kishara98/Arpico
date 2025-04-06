package com.arpico.order.controller;

import com.arpico.order.model.Order;
import com.arpico.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> allOrders = service.getAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getASingleOrder(@PathVariable int orderId) {
        Optional<Order> order = service.getASingleOrder(orderId);
        if(order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<Order> addANewOrder(@RequestBody Order order) {
        return new ResponseEntity<>(service.createANewOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrderDetails(@PathVariable int orderId, @RequestBody Order order) {
        Order updatedOrder = service.updateOrderDetailsById(orderId, order);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Order> removeAOrder(@PathVariable int orderId) {
        service.deleteOrderById(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
