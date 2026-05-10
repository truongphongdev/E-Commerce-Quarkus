package com.phong.it.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.phong.it.dto.response.ProductVariantResponseDTO;
import com.phong.it.entity.ProductVariant;

@Mapper(componentModel = "jakarta", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductVariantMapper {

    @Mapping(target = "productId", source = "product.id")
    ProductVariantResponseDTO toResponseDTO(ProductVariant productVariant);
}
