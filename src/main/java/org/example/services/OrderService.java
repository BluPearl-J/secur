package org.example.services;
import org.example.dtos.requests.OrderRequest;
import org.example.dtos.responses.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse add(OrderRequest request);
    OrderResponse getById(String id);
    List<OrderResponse> getAll();
    OrderResponse update(String id, OrderRequest request);
    OrderResponse delete(String id);
}
