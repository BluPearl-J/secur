package org.example.dtos.responses;

import lombok.Data;
import java.util.List;
@Data
public class CartResponse {
    private String id;
    private List<String> productIds;
    private int totalItems;
    private double totalPrice;
}
