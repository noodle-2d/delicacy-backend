package com.delicacycomics.delicacy.repository;

import com.delicacycomics.delicacy.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Дмитрий on 10/2/2017.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
