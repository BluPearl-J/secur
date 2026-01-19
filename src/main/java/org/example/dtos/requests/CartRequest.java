package org.example.dtos.requests;
import lombok.Data;

import java.util.List;
@Data
public class CartRequest {
    private List<String> productIds;

}
