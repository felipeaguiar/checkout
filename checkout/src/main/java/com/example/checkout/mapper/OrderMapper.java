package com.example.checkout.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.checkout.dto.OrderDto;
import com.example.checkout.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toDto(Order order);
    List<OrderDto> toDto(List<Order> order);

    Order toEntity(OrderDto orderDto);

}

