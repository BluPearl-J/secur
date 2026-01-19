package org.example.dtos.responses;
import lombok.Data;
@Data
public class OrderResponse {
    private String id;
    private String productId;
    private int quantity;
    private double totalPrice;
}
