package com.delicacycomics.delicacy.service;

import com.delicacycomics.delicacy.entity.Product;
import com.delicacycomics.delicacy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

}