package org.example.utils;
import org.example.data.models.Cart;
import org.example.dtos.requests.CartRequest;
import org.example.dtos.responses.CartResponse;

public class CartMapper {

    public static Cart mapToCart(CartRequest request, double totalPrice) {
        Cart cart = new Cart();
        cart.setProductIds(request.getProductIds());
        cart.setTotalItems(request.getProductIds().size());
        cart.setTotalPrice(totalPrice);
        return cart;
    }

    public static CartResponse mapToCartResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setId(cart.getId());
        response.setProductIds(cart.getProductIds());
        response.setTotalItems(cart.getTotalItems());
        response.setTotalPrice(cart.getTotalPrice());
        return response;
    }
}
