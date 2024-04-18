package com.robotdreams.productservice.service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-17T22:55:09+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productRequestDtoToProduct(ProductRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductDetail( mapToProductDetail( dto.productDetail() ) );
        product.setName( dto.name() );
        product.setCategory( dto.category() );
        product.setPhotoUrl( dto.photoUrl() );
        product.setDescription( dto.description() );
        product.setPrice( dto.price() );

        afterMapping( product );

        return product;
    }

    @Override
    public ProductResponseDto productToProductResponseDto(Product product) {
        if ( product == null ) {
            return null;
        }

        String name = null;
        String category = null;
        String photoUrl = null;
        String price = null;

        name = product.getName();
        category = product.getCategory();
        photoUrl = product.getPhotoUrl();
        if ( product.getPrice() != null ) {
            price = String.valueOf( product.getPrice() );
        }

        ProductResponseDto productResponseDto = new ProductResponseDto( name, category, photoUrl, price );

        return productResponseDto;
    }
}
