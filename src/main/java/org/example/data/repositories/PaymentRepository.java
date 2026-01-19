package org.example.data.repositories;
import org.example.data.models.Cart;
import org.example.data.models.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {}
