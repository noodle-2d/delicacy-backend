package com.delicacycomics.delicacy.dto.request;

import com.delicacycomics.delicacy.dto.response.ProductDto;
import com.delicacycomics.delicacy.dto.response.SubjectDto;

import java.util.List;

public class AddBookDto extends ProductDto {

    private SubjectDto publisher;
    private SubjectDto publisherLocal;
    private String isbn;
    private String format;
    private Long pagesCount;
    private List<SubjectDto> authors;
    private List<SubjectDto> artists;

    public AddBookDto() {
        super(ProductDto.BOOK_SUBTYPE);
    }

    public SubjectDto getPublisher() {
        return publisher;
    }

    public void setPublisher(SubjectDto publisher) {
        this.publisher = publisher;
    }

    public SubjectDto getPublisherLocal() {
        return publisherLocal;
    }

    public void setPublisherLocal(SubjectDto publisherLocal) {
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

    public List<SubjectDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<SubjectDto> authors) {
        this.authors = authors;
    }

    public List<SubjectDto> getArtists() {
        return artists;
    }

    public void setArtists(List<SubjectDto> artists) {
        this.artists = artists;
    }

}
