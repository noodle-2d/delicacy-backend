package com.delicacycomics.delicacy.entity;

import javax.persistence.*;

/**
 * Created by Дмитрий on 11.08.2017.
 */

@Entity
@Table(name = "attributes")
public class Attribute extends Product {

    private Long height;
    @ManyToOne
    @JoinColumn(name = "manufacturer")
    private Subject manufacturer;
    private String series;
    private String material;

    protected Attribute() {}

    public Attribute(Long height, String series, String material) {
        this.height = height;
        this.series = series;
        this.material = material;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Subject getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Subject manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                ", height=" + height +
                ", manufacturer=" + manufacturer +
                ", series='" + series + '\'' +
                ", material='" + material + '\'' +
                '}';
    }

}
