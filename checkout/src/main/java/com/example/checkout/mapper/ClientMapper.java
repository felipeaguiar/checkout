package com.example.checkout.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.checkout.dto.ClientDto;
import com.example.checkout.model.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toDto(Client client);
    List<ClientDto> toDto(List<Client> client);

    Client toEntity(ClientDto clientDto);

}

