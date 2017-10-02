package com.delicacycomics.delicacy.repository;

import com.delicacycomics.delicacy.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Дмитрий on 10/2/2017.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
