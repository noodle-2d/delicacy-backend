package com.delicacycomics.delicacy.dto.response;

import com.delicacycomics.delicacy.entity.User;
import com.delicacycomics.delicacy.entity.UserStatus;

import java.util.Date;

public class OrderDto {

    private Long id;
    private User user;
    private Date dateOfCreation;
    private UserStatus status;
    private String note;
    private String identifier;

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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
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
        return "OrderDto{" +
                "id=" + id +
                ", user=" + user +
                ", dateOfCreation=" + dateOfCreation +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
