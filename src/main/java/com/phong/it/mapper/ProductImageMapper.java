package com.phong.it.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.phong.it.dto.response.ProductImageResponseDTO;
import com.phong.it.entity.ProductImage;

@Mapper(componentModel = "jakarta", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductImageMapper {

    @Mapping(target = "productId", source = "product.id")
    ProductImageResponseDTO toResponseDTO(ProductImage productImage);
}
