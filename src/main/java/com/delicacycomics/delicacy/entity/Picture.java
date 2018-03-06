package com.delicacycomics.delicacy.entity;

import javax.persistence.*;

/**
 * Created by Дмитрий on 11.08.2017.
 */

@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue
    @Column(name = "picture_id")
    private Long id;
    @Column(name = "show_order")
    private String showOrder;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    protected Picture() {
    }

    public Picture(String showOrder) {
        this.showOrder = showOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(String showOrder) {
        this.showOrder = showOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Picture" +
                "id=" + id +
                ", showOrder='" + showOrder + '\'' +
                '}';
    }

}
