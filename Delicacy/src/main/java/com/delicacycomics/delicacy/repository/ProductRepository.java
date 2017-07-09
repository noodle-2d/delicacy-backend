package com.delicacycomics.delicacy.repository;

import com.delicacycomics.delicacy.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

//    @Query(name = "") // Добавить JPQL запрос для доступа в базу
//    List<Product> findNewestProducts(int quantity);

}