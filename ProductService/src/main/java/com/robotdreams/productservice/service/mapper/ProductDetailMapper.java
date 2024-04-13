package com.robotdreams.productservice.service.mapper;

import com.robotdreams.productservice.dto.ProductDetailRequestDto;
import com.robotdreams.productservice.entity.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductDetailMapper {
    ProductDetailMapper INSTANCE = Mappers.getMapper(ProductDetailMapper.class);

    ProductDetail mapToProductDetail(ProductDetailRequestDto dto);
}
