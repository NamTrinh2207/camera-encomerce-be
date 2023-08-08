package com.example.cameraincome.controller;

import com.example.cameraincome.model.product.Orders;
import com.example.cameraincome.service.product.Orders.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    IOrdersService ordersService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Orders>> showAllOrders() {
        Iterable<Orders> orders = ordersService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Orders> createOrders(@RequestBody Orders orders) {
        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Orders> deleteOrders(@PathVariable Long id) {
        Optional<Orders> orders = ordersService.findById(id);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordersService.remove(id);
        return new ResponseEntity<>(orders.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> editOrders(@PathVariable Long id, @RequestBody Orders orders) {
        Optional<Orders> optionalOrders = ordersService.findById(id);
        if (optionalOrders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orders.setId(optionalOrders.get().getId());
        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrders(@PathVariable Long id) {
        Optional<Orders> optionalOrders = ordersService.findById(id);
        return optionalOrders.map(orders
                -> new ResponseEntity<>(orders, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
