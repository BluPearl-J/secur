package org.example.utils;

import org.example.data.models.Product;
import org.example.dtos.requests.ProductRequest;
import org.example.dtos.responses.ProductResponse;

public class ProductMapper {

    public static Product mapToProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        return product;
    }

    public static ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        return response;
    }
}
