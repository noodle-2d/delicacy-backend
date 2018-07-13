package com.delicacycomics.delicacy.dto.response;

import com.delicacycomics.delicacy.entity.Order;
import com.delicacycomics.delicacy.entity.Product;

public class OrderItemDto {

    private Long orderItemId;
    private Product product;

    private Order order;
    private Integer amount;

    public OrderItemDto() {
    }

    public OrderItemDto(Long orderItemId, Product product, Order order, Integer amount) {
        this.orderItemId = orderItemId;
        this.product = product;
        this.order = order;
        this.amount = amount;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", product=" + product +
                ", order=" + order +
                ", amount=" + amount +
                '}';
    }
}
