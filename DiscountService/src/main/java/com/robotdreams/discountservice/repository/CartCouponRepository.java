package com.robotdreams.discountservice.repository;

import com.robotdreams.discountservice.entity.CartCoupon;
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
