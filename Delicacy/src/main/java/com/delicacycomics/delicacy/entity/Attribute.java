package com.delicacycomics.delicacy.entity;

import javax.persistence.*;

/**
 * Created by Дмитрий on 11.08.2017.
 */

@Entity
@Table(name = "attributes")
public class Attribute extends Product {

    private String type;
    private Long heigh;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject manufacturer;
    private String series;
    private String material;
    private String text;

    protected Attribute() {}

    public Attribute(String type, Long heigh, Long manufacturer, String series, String material, String text) {
        this.type = type;
        this.heigh = heigh;
        this.series = series;
        this.material = material;
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                ", type='" + type + '\'' +
                ", heigh=" + heigh +
                ", manufacturer=" + manufacturer +
                ", series='" + series + '\'' +
                ", material='" + material + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
