package org.example.services;
import org.example.data.models.Order;
import org.example.data.models.Product;
import org.example.data.repositories.OrderRepository;
import org.example.data.repositories.ProductRepository;
import org.example.dtos.requests.OrderRequest;
import org.example.dtos.responses.OrderResponse;
import org.example.utils.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public OrderResponse add(OrderRequest request) {
        Optional<Product> productOpt = productRepository.findById(request.getProductId());
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            Order order = OrderMapper.mapToOrder(request, product.getPrice());
            order = orderRepository.save(order);
            return OrderMapper.mapToOrderResponse(order);
        }
        return null;
    }

    @Override
    public OrderResponse getById(String id) {
        Optional<Order> optional = orderRepository.findById(id);
        return optional.map(OrderMapper::mapToOrderResponse).orElse(null);
    }

    @Override
    public List<OrderResponse> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> responses = new ArrayList<>();
        for (Order order : orders) {
            responses.add(OrderMapper.mapToOrderResponse(order));
        }
        return responses;
    }

    @Override
    public OrderResponse update(String id, OrderRequest request) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            Order order = optional.get();
            Optional<Product> productOpt = productRepository.findById(request.getProductId());
            if (productOpt.isPresent()) {
                Product product = productOpt.get();
                order.setProductId(request.getProductId());
                order.setQuantity(request.getQuantity());
                order.setTotalPrice(product.getPrice() * request.getQuantity());
                orderRepository.save(order);
                return OrderMapper.mapToOrderResponse(order);
            }
        }
        return null;
    }

    @Override
    public OrderResponse delete(String id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            Order order = optional.get();
            orderRepository.delete(order);
            return OrderMapper.mapToOrderResponse(order);
        }
        return null;
    }
}
