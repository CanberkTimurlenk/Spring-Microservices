package com.robotdreams.discountservice.repository;

import com.robotdreams.discountservice.entity.Coupon;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends ListCrudRepository<Coupon, Long> {

}
