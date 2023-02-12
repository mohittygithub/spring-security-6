package com.tyagi.springsecurity6.serviceImpl;

import com.tyagi.springsecurity6.dto.ApiResponse;
import com.tyagi.springsecurity6.model.Product;
import com.tyagi.springsecurity6.repository.ProductRepository;
import com.tyagi.springsecurity6.service.ProductService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public ApiResponse findAll() {
        List<Product> products = productRepository.findAll();
        return new ApiResponse(null, "", true, products.size(), products);
    }


    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ApiResponse create(Product product) {
        Product existingProduct = productRepository.findBySerialNumber(product.getSerialNumber()).orElse(null);

        if (existingProduct != null) {
            existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            productRepository.save(existingProduct);
            return new ApiResponse(existingProduct.getId(), "", true, 1, Arrays.asList(existingProduct));
        }

        productRepository.save(product);

        return new ApiResponse(product.getId(), "", true, 1, Arrays.asList(product));
    }

    @Override
    public ApiResponse createMany(List<Product> products) {
        List<Product> existingProducts = productRepository.findBySerialNumbers(products.stream().map(product -> product.getSerialNumber()).collect(Collectors.toSet()));

        Map<UUID, Product> existingProductsMap = new HashMap<>();
        for (Product product : existingProducts) {
            existingProductsMap.put(product.getSerialNumber(), product);
        }

        List<Product> newProducts = new ArrayList<>();
        for (Product product : products) {
            if (!existingProductsMap.containsKey(product.getSerialNumber())) {
                newProducts.add(product);
            } else {
                Product existingProduct = existingProductsMap.get(product.getSerialNumber());
                existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
                existingProduct.setPrice(product.getPrice());
            }
        }

        List<Product> finalProductsList = new ArrayList<>();
        finalProductsList.addAll(newProducts);
        finalProductsList.addAll(existingProductsMap.values());

        productRepository.saveAll(finalProductsList);

        return new ApiResponse(null, "", true, finalProductsList.size(), finalProductsList);
    }

    @Override
    public ApiResponse findById(UUID id) {
        Product product =  productRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("No Product Found"));

        return new ApiResponse(product.getId(), "", true, 1, Arrays.asList(product));
    }
}
