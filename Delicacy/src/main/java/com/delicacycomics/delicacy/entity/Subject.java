package com.delicacycomics.delicacy.entity;

import javax.persistence.*;

/**
 * Created by Дмитрий on 11.08.2017.
 */

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "type")
    private Book book;

    protected Subject() {}

    public Subject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
