package com.microservices.discountservice.repository;

import com.microservices.discountservice.entity.CartCoupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartCouponRepository extends MongoRepository<CartCoupon, Long> {

    List<CartCoupon> findAllByExpirationDateAfter(Date date);

    Optional<CartCoupon> findByIdAndExpirationDateAfter(Long id, Date date);

}
