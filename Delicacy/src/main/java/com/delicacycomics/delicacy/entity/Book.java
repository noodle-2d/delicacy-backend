package com.delicacycomics.delicacy.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Дмитрий on 11.08.2017.
 */

@Entity
@Table(name = "books")
public class Book extends Product {

    @ManyToOne
    @JoinColumn(name = "publisher")
    private Subject publisher;
    @ManyToOne
    @JoinColumn(name = "publisher_local")
    private Subject publisherLocal;
    private String isbn;
    private String format;
    @Column(name = "pages_count")
    private Long pagesCount;
    @ManyToMany
    @JoinTable(name = "products_authors",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> authors;
    @ManyToMany
    @JoinTable(name = "products_artists",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> artists;

    protected Book(){}

    public Book(String isbn, String format, Long pagesCount) {
        this.isbn = isbn;
        this.format = format;
        this.pagesCount = pagesCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Long getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Long pagesCount) {
        this.pagesCount = pagesCount;
    }

    public List<Subject> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Subject> authors) {
        this.authors = authors;
    }

    public List<Subject> getArtists() {
        return artists;
    }

    public void setArtists(List<Subject> artists) {
        this.artists = artists;
    }

    public Subject getPublisher() {
        return publisher;
    }

    public void setPublisher(Subject publisher) {
        this.publisher = publisher;
    }

    public Subject getPublisherLocal() {
        return publisherLocal;
    }

    public void setPublisherLocal(Subject publisherLocal) {
        this.publisherLocal = publisherLocal;
    }

    @Override
    public String toString() {
        return "Book{" +
                ", publisher=" + publisher +
                ", publisherLocal=" + publisherLocal +
                ", isbn='" + isbn + '\'' +
                ", format='" + format + '\'' +
                ", pagesCount=" + pagesCount +
                '}';
    }

}
