package com.delicacycomics.delicacy.rest.controller;

import com.delicacycomics.delicacy.entity.Product;
import com.delicacycomics.delicacy.repository.ProductRepository;
import com.delicacycomics.delicacy.rest.response.ProductsGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RestController
public class MainPageController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(name = "/newest", method = RequestMethod.GET)
    public ProductsGroup newestProducts(@RequestParam(name = "quantity", defaultValue = "5") int quantity) {
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
        return new ProductsGroup(productsList);
    }

}