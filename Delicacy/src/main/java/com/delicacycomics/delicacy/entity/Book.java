package com.delicacycomics.delicacy.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Дмитрий on 11.08.2017.
 */

@Entity
@Table(name = "books")
public class Book extends Product {

    private Long publisher;
    @Column(name = "publisher_local")
    private Long publisherLocal;
    private String isbn;
    @Column(name = "synopsis_text")
    private String synopsisText;
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
    @ManyToOne
    @JoinColumn(name = "type")
    private Subject type;

    protected Book(){}

    public Book(Long publisher, Long publisherLocal, String isbn, String synopsisText, String format, Long pagesCount) {
        this.publisher = publisher;
        this.publisherLocal = publisherLocal;
        this.isbn = isbn;
        this.synopsisText = synopsisText;
        this.format = format;
        this.pagesCount = pagesCount;
    }

    public Subject getType() {
        return type;
    }

    public void setType(Subject type) {
        this.type = type;
    }

    public Long getPublisher() {
        return publisher;
    }

    public void setPublisher(Long publisher) {
        this.publisher = publisher;
    }

    public Long getPublisherLocal() {
        return publisherLocal;
    }

    public void setPublisherLocal(Long publisherLocal) {
        this.publisherLocal = publisherLocal;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSynopsisText() {
        return synopsisText;
    }

    public void setSynopsisText(String synopsisText) {
        this.synopsisText = synopsisText;
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

    @Override
    public String toString() {
        return "Book{" +
                ", type=" + type +
                ", publisher=" + publisher +
                ", publisherLocal=" + publisherLocal +
                ", isbn='" + isbn + '\'' +
                ", synopsisText='" + synopsisText + '\'' +
                ", format='" + format + '\'' +
                ", pagesCount=" + pagesCount +
                '}';
    }
}
