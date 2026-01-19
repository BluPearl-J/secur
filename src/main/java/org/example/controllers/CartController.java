package org.example.controllers;

import org.example.dtos.requests.CartRequest;
import org.example.dtos.responses.CartResponse;
import org.example.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public CartResponse add(@RequestBody CartRequest request) {
        return cartService.add(request);
    }

    @GetMapping("/{id}")
    public CartResponse getById(@PathVariable String id) {
        return cartService.getById(id);
    }

    @GetMapping
    public List<CartResponse> getAll() {
        return cartService.getAll();
    }

    @PutMapping("/{id}")
    public CartResponse update(@PathVariable String id, @RequestBody CartRequest request) {
        return cartService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public CartResponse delete(@PathVariable String id) {
        return cartService.delete(id);
    }
}
