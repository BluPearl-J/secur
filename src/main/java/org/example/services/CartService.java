package org.example.services;

import org.example.dtos.requests.CartRequest;
import org.example.dtos.responses.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse add(CartRequest request);
    CartResponse getById(String id);
    List<CartResponse> getAll();
    CartResponse update(String id, CartRequest request);
    CartResponse delete(String id);
}
