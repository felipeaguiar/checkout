package com.example.checkout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.checkout.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}


