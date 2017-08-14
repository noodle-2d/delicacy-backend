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
    private String show_order;

    protected Picture() {
    }

    public Picture(Long idOfProduct, String show_order) {
        this.idOfProduct = idOfProduct;
        this.show_order = show_order;
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

    public String getShow_order() {
        return show_order;
    }

    public void setShow_order(String show_order) {
        this.show_order = show_order;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", idOfProduct=" + idOfProduct +
                ", show_order='" + show_order + '\'' +
                '}';
    }
}
