package com.delicacycomics.delicacy.service;

import com.delicacycomics.delicacy.entity.Product;
import com.delicacycomics.delicacy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<Product> findNewestProducts(int quantity) {
        Set<Product> productsSet = new TreeSet<>((firstProduct, secondProduct) ->
                -Long.compare(firstProduct.getId(), secondProduct.getId())
        );
        productRepository.findAll().forEach(productsSet::add);
        List<Product> productsList = new ArrayList<>();
        for (Product product: productsSet) {
            if (productsList.size() < quantity) {
                productsList.add(product);
            } else {
                break;
            }
        }
        return productsList;
    }

}