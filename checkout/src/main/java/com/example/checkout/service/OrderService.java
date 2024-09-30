package com.example.checkout.service;

import java.util.List;

import com.example.checkout.model.Order;

public interface OrderService {
  
    Order get(Long id);

    List<Order> list();

    Order create(Order order);

    void checkout(Long id); 
    
}
