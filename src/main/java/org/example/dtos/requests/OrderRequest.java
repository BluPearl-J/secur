package org.example.dtos.requests;
import lombok.Data;

@Data
public class OrderRequest {
    private String productId;
    private int quantity;

}
