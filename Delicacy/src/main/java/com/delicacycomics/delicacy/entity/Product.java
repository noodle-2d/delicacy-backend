package com.delicacycomics.delicacy.entity;

import javax.persistence.*;
import java.util.Date;

@Entity // Сказать ORM, что этот класс - сущность
@Table(name = "products") // Указать, с какой таблицей мапить
public class Product {

    @Id // Указывает, что это поле - первичный ключ
    @GeneratedValue // Указывает, что в это поле нужно генерировать уникальное значение
    @Column(name = "product_id") // С каким полем мапить
    private Long id;
    private String title;
    private Double price;
    @Temporal(TemporalType.DATE)
    private Date date;
    private Long remainder;

    protected Product() { }

    public Product(String title, Double price, Long remainder) {
        this.title = title;
        this.price = price;
        this.remainder = remainder;
    }

    public Product(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getRemainder() {
        return remainder;
    }

    public void setRemainder(Long remainder) {
        this.remainder = remainder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id != null ? id.equals(product.id) : product.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", remainder=" + remainder +
                '}';
    }

}