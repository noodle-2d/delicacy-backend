package com.delicacycomics.delicacy.entity;

import javax.persistence.*;

/**
 * Created by Дмитрий on 11.08.2017.
 */

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;
    private Long type;
    private Long publisher;
    private Long publisher_local;
    private String isbn;
    private String synopsis_text;
    private String format;
    private Long pages_count;

    protected Book(){}

    public Book(Long type, Long publisher, Long publisher_local, String isbn, String synopsis_text, String format, Long pages_count) {
        this.type = type;
        this.publisher = publisher;
        this.publisher_local = publisher_local;
        this.isbn = isbn;
        this.synopsis_text = synopsis_text;
        this.format = format;
        this.pages_count = pages_count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getPublisher() {
        return publisher;
    }

    public void setPublisher(Long publisher) {
        this.publisher = publisher;
    }

    public Long getPublisher_local() {
        return publisher_local;
    }

    public void setPublisher_local(Long publisher_local) {
        this.publisher_local = publisher_local;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSynopsis_text() {
        return synopsis_text;
    }

    public void setSynopsis_text(String synopsis_text) {
        this.synopsis_text = synopsis_text;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Long getPages_count() {
        return pages_count;
    }

    public void setPages_count(Long pages_count) {
        this.pages_count = pages_count;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", type=" + type +
                ", publisher=" + publisher +
                ", publisher_local=" + publisher_local +
                ", isbn='" + isbn + '\'' +
                ", synopsis_text='" + synopsis_text + '\'' +
                ", format='" + format + '\'' +
                ", pages_count=" + pages_count +
                '}';
    }
}
