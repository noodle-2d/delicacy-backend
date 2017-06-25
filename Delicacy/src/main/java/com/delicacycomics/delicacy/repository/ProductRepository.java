package com.delicacycomics.delicacy.repository;

import com.delicacycomics.delicacy.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}