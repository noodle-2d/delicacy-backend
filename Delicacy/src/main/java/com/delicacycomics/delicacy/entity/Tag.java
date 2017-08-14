package com.delicacycomics.delicacy.entity;

import javax.persistence.*;

/**
 * Created by Дмитрий on 11.08.2017.
 */

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long id;
    private String title;

    protected Tag() {
    }

    public Tag(String title) {
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

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
