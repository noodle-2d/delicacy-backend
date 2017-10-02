package com.delicacycomics.delicacy.repository;

import com.delicacycomics.delicacy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface    ProductRepository<T extends Product> extends JpaRepository<T, Long> {

    ///JpaSpecificationExecutor<T> what

}
