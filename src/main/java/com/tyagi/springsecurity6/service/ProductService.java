package com.tyagi.springsecurity6.service;

import com.tyagi.springsecurity6.dto.ApiResponse;
import com.tyagi.springsecurity6.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ApiResponse findAll();
    ApiResponse create(Product product);

    ApiResponse createMany(List<Product> products);

    ApiResponse findById(UUID id);
}
