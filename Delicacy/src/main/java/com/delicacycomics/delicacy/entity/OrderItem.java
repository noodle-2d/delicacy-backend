package com.delicacycomics.delicacy.entity;

import javax.persistence.*;

/**
 * Created by Дмитрий on 9/21/2017.
 */
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Column(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @Column(name = "order_id")
    private Order order;

    private Long amount;

    public OrderItem() {
    }

    public OrderItem(Long orderItemId, Product product, Order order, Long amount) {
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
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
