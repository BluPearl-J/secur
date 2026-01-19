package org.example.services;

import org.example.data.models.Cart;
import org.example.data.models.Product;
import org.example.data.repositories.CartRepository;
import org.example.data.repositories.ProductRepository;
import org.example.dtos.requests.CartRequest;
import org.example.dtos.responses.CartResponse;
import org.example.utils.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CartResponse add(CartRequest request) {
        double totalPrice = 0;
        for (String productId : request.getProductIds()) {
            Optional<Product> productOpt = productRepository.findById(productId);
            if (productOpt.isPresent()) {
                totalPrice += productOpt.get().getPrice();
            }
        }
        Cart cart = CartMapper.mapToCart(request, totalPrice);
        cart = cartRepository.save(cart);
        return CartMapper.mapToCartResponse(cart);
    }

    @Override
    public CartResponse getById(String id) {
        Optional<Cart> optional = cartRepository.findById(id);
        return optional.map(CartMapper::mapToCartResponse).orElse(null);
    }

    @Override
    public List<CartResponse> getAll() {
        List<Cart> carts = cartRepository.findAll();
        List<CartResponse> responses = new ArrayList<>();
        for (Cart cart : carts) {
            responses.add(CartMapper.mapToCartResponse(cart));
        }
        return responses;
    }

    @Override
    public CartResponse update(String id, CartRequest request) {
        Optional<Cart> optional = cartRepository.findById(id);
        if (optional.isPresent()) {
            double totalPrice = 0;
            for (String productId : request.getProductIds()) {
                Optional<Product> productOpt = productRepository.findById(productId);
                if (productOpt.isPresent()) {
                    totalPrice += productOpt.get().getPrice();
                }
            }
            Cart cart = optional.get();
            cart.setProductIds(request.getProductIds());
            cart.setTotalItems(request.getProductIds().size());
            cart.setTotalPrice(totalPrice);
            cartRepository.save(cart);
            return CartMapper.mapToCartResponse(cart);
        }
        return null;
    }

    @Override
    public CartResponse delete(String id) {
        Optional<Cart> optional = cartRepository.findById(id);
        if (optional.isPresent()) {
            Cart cart = optional.get();
            cartRepository.delete(cart);
            return CartMapper.mapToCartResponse(cart);
        }
        return null;
    }
}
