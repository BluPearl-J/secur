package org.example.controllers;


import org.example.dtos.requests.ProductRequest;
import org.example.dtos.responses.ProductResponse;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponse add(@RequestBody ProductRequest request) {
        return productService.add(request);
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable String id) {
        return productService.getById(id);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable String id, @RequestBody ProductRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ProductResponse delete(@PathVariable String id) {
        return productService.delete(id);
    }
    @PostMapping("/import") public List<ProductResponse> importProducts() {
        return productService.importFromFakeStore();
    }


}
