package com.microservices.productservice.service;

import com.microservices.productservice.dto.ProductRequestDto;
import com.microservices.productservice.dto.ProductResponseDto;
import com.microservices.productservice.entity.Product;
import com.microservices.productservice.exceptionHandling.GeneralException;
import com.microservices.productservice.exceptionHandling.ProductException;
import com.microservices.productservice.repository.ProductRepository;
import com.microservices.productservice.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public boolean save(ProductRequestDto createProductRequestDto) {
        Product product = mapper.productRequestDtoToProduct(createProductRequestDto);

        try {
            checkIfPriceIsValid(createProductRequestDto.price());
        } catch (ProductException e) {
            System.out.println("Logged!");
            throw new GeneralException("Invalid price");
        }
        return productRepository.save(product).getId() > 0;
    }

    public boolean saveAll(List<ProductRequestDto> createProductRequestDtos) {

        createProductRequestDtos.forEach(p -> {
            try {
                checkIfPriceIsValid(p.price());
            } catch (ProductException e) {
                System.out.println("Logged!");
                throw new GeneralException("Invalid price");
            }
        });

        List<Product> productsToSave = createProductRequestDtos.stream().map(mapper::productRequestDtoToProduct).toList();
        productRepository.saveAll(productsToSave);
        return true;
    }

    public List<ProductResponseDto> findAll(List<Long> productIds) {


        if (productIds == null || productIds.isEmpty())
            return productRepository.findAll().stream()
                    .map(mapper::productToProductResponseDto).toList();


        return productRepository.findByIdIn(productIds).stream()
                .map(mapper::productToProductResponseDto).toList();
    }

    private void checkIfPriceIsValid(double price)
            throws ProductException {

        if (price <= 0.0)
            throw new ProductException("the price is invalid: " + price);
    }

    public Product findById(long id) {
        return productRepository.findById(id).get();
    }

    public List<ProductResponseDto> findByCategory(String category) {

        return productRepository.findByCategory(category).stream()
                .map(mapper::productToProductResponseDto).toList();
    }

    public Boolean checkIfProductIsValid(long productId) {
        return productRepository.existsById(productId);
    }

}
