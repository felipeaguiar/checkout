package com.example.checkout.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.checkout.dto.OrderDto;
import com.example.checkout.mapper.OrderMapper;
import com.example.checkout.model.Order;
import com.example.checkout.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> list() {
        List<Order> orders = orderService.list();
        List<OrderDto> ordersDto = orderMapper.toDto(orders);

        return ResponseEntity.ok(ordersDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> get(@PathVariable Long id) {
        Order order = orderService.get(id);
        OrderDto orderDto = orderMapper.toDto(order);

        return ResponseEntity.ok(orderDto);
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order orderSalvo = orderService.create(order);
        OrderDto orderSalvoDto = orderMapper.toDto(orderSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderSalvoDto);
    }
    
}
