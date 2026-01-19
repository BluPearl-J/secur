package org.example.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "shipments")
public class Shipment {
    @Id
    private String id;
    private String orderId;
    private String address;
    private String status;
}
