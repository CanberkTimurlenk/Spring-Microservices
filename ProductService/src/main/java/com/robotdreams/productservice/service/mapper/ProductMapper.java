package com.robotdreams.productservice.service.mapper;

import com.robotdreams.productservice.dto.ProductDetailRequestDto;
import com.robotdreams.productservice.dto.ProductRequestDto;
import com.robotdreams.productservice.dto.ProductResponseDto;
import com.robotdreams.productservice.entity.Product;
import com.robotdreams.productservice.entity.ProductDetail;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    @Mapping(target = "productDetail", source = "productDetail", qualifiedByName = "mapToProductDetail")
    Product productRequestDtoToProduct(ProductRequestDto dto);

    ProductResponseDto productToProductResponseDto(Product product);

    @Named("mapToProductDetail")
    default ProductDetail mapToProductDetail(ProductDetailRequestDto dto) {
        return ProductDetailMapper.INSTANCE.mapToProductDetail(dto);
    }

    @AfterMapping
    default void afterMapping(@MappingTarget Product product) {
        var detail = product.getProductDetail();
        detail.setProduct(product);
    }
}
