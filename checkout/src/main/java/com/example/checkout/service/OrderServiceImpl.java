package com.example.checkout.service;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.checkout.dto.OrderMessageDto;
import com.example.checkout.model.Order;
import com.example.checkout.model.Status;
import com.example.checkout.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  
    private final OrderRepository orderRepository;
    private final AmqpTemplate amqpTemplate;

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

    public void checkout(Long id) {
        Order order = get(id);
        amqpTemplate.convertAndSend("payments", order.getId().toString());
    }

    @RabbitListener(queues = "payment_response")
    public void processPaymentResponse(String messageJson) throws JsonProcessingException { 
        ObjectMapper objectMapper = new ObjectMapper();
        OrderMessageDto message  = objectMapper.readValue(messageJson, OrderMessageDto.class);

        Order order = get(message.getOrderId());

        if ("APPROVED".equals(message.getStatus())) {
            order.setStatus(Status.APPROVED);   
        } else {
            order.setStatus(Status.CANCELED);
        }

        orderRepository.save(order);
    }
    
}
