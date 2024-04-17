package com.robotdreams.productservice.service;

import com.robotdreams.productservice.dto.ProductRequestDto;
import com.robotdreams.productservice.dto.ProductResponseDto;
import com.robotdreams.productservice.entity.Product;
import com.robotdreams.productservice.exceptionHandling.GeneralException;
import com.robotdreams.productservice.exceptionHandling.ProductException;
import com.robotdreams.productservice.repository.ProductRepository;
import com.robotdreams.productservice.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        createProductRequestDtos.stream().forEach(p -> {
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

}
