package com.example.checkout.dto;

import lombok.Data;

@Data
public class OrderMessageDto {
    private Long orderId;
    private String status;
}
