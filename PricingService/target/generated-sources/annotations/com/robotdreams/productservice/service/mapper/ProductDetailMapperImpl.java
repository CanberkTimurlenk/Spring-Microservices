package com.robotdreams.productservice.service.mapper;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-17T22:55:08+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
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
