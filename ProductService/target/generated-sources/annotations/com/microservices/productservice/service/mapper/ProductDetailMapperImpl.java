package com.microservices.productservice.service.mapper;

import com.microservices.productservice.dto.ProductDetailRequestDto;
import com.microservices.productservice.entity.ProductDetail;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-26T11:35:12+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class ProductDetailMapperImpl implements ProductDetailMapper {

    @Override
    public ProductDetail mapToProductDetail(ProductDetailRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProductDetail productDetail = new ProductDetail();

        productDetail.setProductInfo( dto.productInfo() );
        productDetail.setProductSerialNumber( dto.productSerialNumber() );

        return productDetail;
    }
}
