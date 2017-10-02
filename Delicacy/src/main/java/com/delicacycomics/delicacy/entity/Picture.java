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
    private Long idOfProduct;
    @Column(name = "show_order")
    private String showOrder;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    protected Picture() {
    }

    public Picture(Long idOfProduct, String showOrder) {
        this.idOfProduct = idOfProduct;
        this.showOrder = showOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOfProduct() {
        return idOfProduct;
    }

    public void setIdOfProduct(Long idOfProduct) {
        this.idOfProduct = idOfProduct;
    }

    public String getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(String showOrder) {
        this.showOrder = showOrder;
    }

    @Override
    public String toString() {
        return "Picture" +
                "id=" + id +
                ", idOfProduct=" + idOfProduct +
                ", showOrder='" + showOrder + '\'' +
                '}';
    }
}
