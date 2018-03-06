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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "date_of_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfCreation;
    private String status;
    private String note;
    private String identifier;

    protected Order() {
    }

    public Order(Date dateOfCreation, String status, String note) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", dateOfCreation=" + dateOfCreation +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

}
