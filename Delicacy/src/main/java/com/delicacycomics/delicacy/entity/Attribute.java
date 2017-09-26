package com.delicacycomics.delicacy.entity;

import javax.persistence.*;

/**
 * Created by Дмитрий on 11.08.2017.
 */

@Entity
@Table(name = "attributes")
public class Attribute extends Product {

    private Long heigh;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject manufacturer;
    private String series;
    private String material;

    protected Attribute() {}

    public Attribute(Long heigh, Long manufacturer, String series, String material) {
        this.heigh = heigh;
        this.series = series;
        this.material = material;
    }

    public Long getHeigh() {
        return heigh;
    }

    public void setHeigh(Long heigh) {
        this.heigh = heigh;
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


    @Override
    public String toString() {
        return "Attribute{" +
                ", heigh=" + heigh +
                ", manufacturer=" + manufacturer +
                ", series='" + series + '\'' +
                ", material='" + material + '\'' +
                '}';
    }

    public void setManufacturer(Subject manufacturer) {
        this.manufacturer = manufacturer;
    }
}
