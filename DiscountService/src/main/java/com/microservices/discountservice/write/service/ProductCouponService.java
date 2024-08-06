package com.microservices.discountservice.write.service;

import com.microservices.discountservice.dto.ProductCouponRequestDto;
import com.microservices.discountservice.dto.ProductCouponResponseDto;
import com.microservices.discountservice.entity.ProductCoupon;
import com.microservices.discountservice.exceptionHandling.BusinessException;
import com.microservices.discountservice.feign.ProductFeignClient;
import com.microservices.discountservice.write.repository.ProductCouponRepository;
import com.microservices.discountservice.write.service.mapper.ProductCouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCouponService {

    private final ProductCouponRepository productCouponRepository;
    private final ProductCouponMapper productCouponMapper;
    private final ProductFeignClient productClient;

    public Long save(ProductCouponRequestDto productCouponRequestDto) {

        var productExists = productClient.checkIfProductExists(productCouponRequestDto.productId());

        if (productExists == null || !productExists)
            throw new BusinessException("A product does not exist for id: " + productCouponRequestDto.productId());

        ProductCoupon productCoupon = productCouponMapper.productCouponRequestDtoToProductCoupon(productCouponRequestDto);
        return productCouponRepository.save(productCoupon).getId();
    }

    public boolean deleteByID(long productCouponId) {

        if (!productCouponRepository.existsById(productCouponId))
            throw new BusinessException("The coupon does not exist with id: " + productCouponId);

        productCouponRepository.deleteById(productCouponId);
        return true;
    }

    public Optional<ProductCouponResponseDto> update(long productCouponId, ProductCouponRequestDto productCouponRequestDto) {

        return productCouponRepository
                .findByIdAndExpirationDateAfter(productCouponId, new Date())
                .map(productCoupon ->{
                    productCouponMapper.updateProductCoupon(productCoupon,productCouponRequestDto);
                    productCouponRepository.save(productCoupon);
                    return productCouponMapper.productCouponToProductCouponResponseDto(productCoupon);
                });
    }

    public Optional<ProductCouponResponseDto> findById(long productCouponId) {

        return productCouponRepository.findByIdAndExpirationDateAfter(productCouponId, new Date())
                .map(productCouponMapper::productCouponToProductCouponResponseDto);
    }

    public List<ProductCouponResponseDto> findAll() {

        return productCouponRepository.findAllByExpirationDateAfter(new Date()).stream()
                .map(productCouponMapper::productCouponToProductCouponResponseDto).toList();
    }

}
