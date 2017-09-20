package com.delicacycomics.delicacy.repository;

import com.delicacycomics.delicacy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseProductRepository<T extends Product> extends JpaRepository<T, Long>,
        JpaSpecificationExecutor<T> {
}
