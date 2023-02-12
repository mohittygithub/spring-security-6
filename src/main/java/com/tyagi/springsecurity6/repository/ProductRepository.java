package com.tyagi.springsecurity6.repository;

import com.tyagi.springsecurity6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findBySerialNumber(UUID serialNumber);


    @Query(value = "SELECT * FROM Product WHERE serialNumber IN ?1",nativeQuery = true)
    List<Product> findBySerialNumbers(Set<UUID> serialNumbers);
}
