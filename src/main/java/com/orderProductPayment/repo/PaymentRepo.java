package com.orderProductPayment.repo;

import com.orderProductPayment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment , Integer> {
}

