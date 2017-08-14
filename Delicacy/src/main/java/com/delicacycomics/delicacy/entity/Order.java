package com.delicacycomics.delicacy.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Дмитрий on 11.08.2017.
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "date_of_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfCreation;
    private String status;
    private String note;

    protected Order() {
    }

    public Order(long userId, Date dateOfCreation, String status, String note) {
        this.userId = userId;
        this.dateOfCreation = dateOfCreation;
        this.status = status;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", dateOfCreation=" + dateOfCreation +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
