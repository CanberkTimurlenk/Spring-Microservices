package com.microservices.inventoryservice.utils;

import com.microservices.inventoryservice.repository.InventoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilTest {

    private final InventoryRepository inventoryRepository;

    @PostConstruct
    public void test() {

    }

}
