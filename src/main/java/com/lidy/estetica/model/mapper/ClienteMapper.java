package com.lidy.estetica.model.mapper;

import com.lidy.estetica.model.Cliente;
import com.lidy.estetica.model.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toEntity(ClienteDTO clienteDTO);
}
