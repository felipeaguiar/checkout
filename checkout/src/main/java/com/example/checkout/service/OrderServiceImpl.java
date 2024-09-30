package com.example.checkout.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.checkout.model.Order;
import com.example.checkout.model.Status;
import com.example.checkout.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  
    private final OrderRepository orderRepository;

    @Override
    public Order get(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Override
    public List<Order> list() {
        return orderRepository.findAll();
    }

    @Override
    public Order create(Order order) {
        log.info("Create order", order);
        order.setStatus(Status.PENDING);

        return orderRepository.save(order);
    }
    
}
