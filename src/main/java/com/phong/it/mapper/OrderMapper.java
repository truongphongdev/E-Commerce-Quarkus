package com.phong.it.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.phong.it.dto.request.OrderRequestDTO;
import com.phong.it.dto.response.OrderResponseDTO;
import com.phong.it.entity.Order;

@Mapper(
    componentModel = "jakarta", 
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {OrderItemMapper.class}
)
public interface OrderMapper {

    @Mapping(target = "userId", source = "user.id")
    OrderResponseDTO toResponseDTO(Order order);

    @Mapping(target = "orderItems", source = "items")
    Order toEntity(OrderRequestDTO dto);
}
