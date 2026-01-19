package org.example.utils;
import org.example.data.models.Order;
import org.example.dtos.requests.OrderRequest;
import org.example.dtos.responses.OrderResponse;


public class OrderMapper {

    public static Order mapToOrder(OrderRequest request, double productPrice) {
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(productPrice * request.getQuantity());
        return order;
    }

    public static OrderResponse mapToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setProductId(order.getProductId());
        response.setQuantity(order.getQuantity());
        response.setTotalPrice(order.getTotalPrice());
        return response;
    }
}
