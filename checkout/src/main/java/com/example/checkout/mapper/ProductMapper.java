package com.example.checkout.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.checkout.dto.ProductDto;
import com.example.checkout.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);
    List<ProductDto> toDto(List<Product> product);

    Product toEntity(ProductDto productDto);

}

