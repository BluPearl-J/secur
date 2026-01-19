package org.example.services;

import org.example.data.models.Product;
import org.example.data.repositories.ProductRepository;
import org.example.dtos.requests.ProductRequest;
import org.example.dtos.responses.ProductResponse;
import org.example.exceptions.FakeStoreImportException;
import org.example.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse add(ProductRequest request) {
        Product product = ProductMapper.mapToProduct(request);
        product = productRepository.save(product);
        return ProductMapper.mapToProductResponse(product);
    }

    @Override
    public ProductResponse getById(String id) {
        Optional<Product> optional = productRepository.findById(id);
        return optional.map(ProductMapper::mapToProductResponse).orElse(null);
    }

    @Override
    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> responses = new ArrayList<>();
        for (Product product : products) {
            responses.add(ProductMapper.mapToProductResponse(product));
        }
        return responses;
    }

    @Override
    public ProductResponse update(String id, ProductRequest request) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setStock(request.getStock());
            productRepository.save(product);
            return ProductMapper.mapToProductResponse(product);
        }
        return null;
    }

    @Override
    public ProductResponse delete(String id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            productRepository.delete(product);
            return ProductMapper.mapToProductResponse(product);
        }
        return null;
    }

    @Override
    public List<ProductResponse> importFromFakeStore() {
        List<ProductResponse> responses = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://fakestoreapi.com/products";
            ProductRequest[] fakeProducts = restTemplate.getForObject(url, ProductRequest[].class);

            if (fakeProducts != null) {
                for (ProductRequest request : fakeProducts) {
                    Product product = ProductMapper.mapToProduct(request);
                    product = productRepository.save(product);
                    responses.add(ProductMapper.mapToProductResponse(product));
                }
            }
        } catch (Exception e) {
            throw new FakeStoreImportException("Failed to import products from Fake Store API", e);
        }
        return responses;
    }
}

