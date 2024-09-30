package com.example.checkout.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.checkout.dto.ItemDto;
import com.example.checkout.model.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toDto(Item item);
    List<ItemDto> toDto(List<Item> items);

    Item toEntity(ItemDto itemDto);

}

