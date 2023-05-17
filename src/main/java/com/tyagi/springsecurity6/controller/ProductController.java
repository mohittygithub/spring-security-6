package com.tyagi.springsecurity6.controller;

import com.tyagi.springsecurity6.dto.ApiResponse;
import com.tyagi.springsecurity6.model.Product;
import com.tyagi.springsecurity6.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/test")
    public ApiResponse test(){
        return new ApiResponse(null, "Hello World!", true, 1, null);
    }

    @GetMapping
    public ApiResponse findAll(){
        return productService.findAll();
    }

    @PostMapping
    public ApiResponse create(@RequestBody Product product){
        return productService.create(product);
    }

    @PostMapping("/bulk")
    @PreAuthorize("hasAnyAuthority('ROLES_ADMIN')")
    public ApiResponse createMany(@RequestBody List<Product> products){
        return productService.createMany(products);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLES_ADMIN','ROLES_USER')")
    public ApiResponse findById(@PathVariable UUID id){
        return productService.findById(id);
    }
}
