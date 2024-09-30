package com.example.checkout.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.checkout.model.Status;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Status status;

    @NotNull
    private ClientDto client;

    @NotEmpty
    private List<ItemDto> items;

}
