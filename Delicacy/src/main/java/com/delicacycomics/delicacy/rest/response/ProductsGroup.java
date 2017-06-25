package com.delicacycomics.delicacy.rest.response;

import com.delicacycomics.delicacy.entity.Product;

import java.util.List;

public class ProductsGroup {

    private List<Product> products;

    public ProductsGroup(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}