package org.example.controllers;
import org.example.dtos.requests.OrderRequest;
import org.example.dtos.responses.OrderResponse;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderResponse add(@RequestBody OrderRequest request) {
        return orderService.add(request);
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable String id) {
        return orderService.getById(id);
    }

    @GetMapping
    public List<OrderResponse> getAll() {
        return orderService.getAll();
    }

    @PutMapping("/{id}")
    public OrderResponse update(@PathVariable String id, @RequestBody OrderRequest request) {
        return orderService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public OrderResponse delete(@PathVariable String id) {
        return orderService.delete(id);
    }
}
