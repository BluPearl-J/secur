package org.example.services;

import org.example.dtos.requests.ProductRequest;
import org.example.dtos.responses.ProductResponse;
import java.util.List;

public interface ProductService {
    ProductResponse add(ProductRequest req);
    ProductResponse getById(String id);
    List<ProductResponse> getAll();
    ProductResponse update(String id, ProductRequest req);
    ProductResponse delete(String id);
    List<ProductResponse> importFromFakeStore();
}
