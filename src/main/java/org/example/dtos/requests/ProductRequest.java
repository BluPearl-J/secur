package org.example.dtos.requests;
import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private double price;
    private int stock;
}
