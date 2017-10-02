package com.delicacycomics.delicacy.dto.response;

import java.util.List;

public class BookDTO extends ProductDTO {

    private SubjectDTO publisher;
    private SubjectDTO publisherLocal;
    private String isbn;
    private String format;
    private Long pagesCount;
    private List<SubjectDTO> authors;
    private List<SubjectDTO> artists;

    public BookDTO() {
        super(ProductDTO.BOOK_SUBTYPE);
    }

    public SubjectDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(SubjectDTO publisher) {
        this.publisher = publisher;
    }

    public SubjectDTO getPublisherLocal() {
        return publisherLocal;
    }

    public void setPublisherLocal(SubjectDTO publisherLocal) {
        this.publisherLocal = publisherLocal;
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

    public List<SubjectDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<SubjectDTO> authors) {
        this.authors = authors;
    }

    public List<SubjectDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<SubjectDTO> artists) {
        this.artists = artists;
    }

}
