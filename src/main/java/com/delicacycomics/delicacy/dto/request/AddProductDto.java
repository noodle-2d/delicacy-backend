package com.delicacycomics.delicacy.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "productSubtype")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddBookDto.class, name = AddProductDto.BOOK_SUBTYPE),
        @JsonSubTypes.Type(value = AddAttributeDto.class, name = AddProductDto.ATTRIBUTE_SUBTYPE)})
abstract public class AddProductDto {

    public final static String BOOK_SUBTYPE = "book";
    public final static String ATTRIBUTE_SUBTYPE = "attribute";

    private Long id;
    private String title;
    private AddSubjectDto type;
    private String description;
    private Double price;
    private Date date;
    private Long remainder;
    private List<AddTagDto> tags;
    private String productSubtype;

    public AddProductDto() { }

    public AddProductDto(String productSubtype) {
        this.productSubtype = productSubtype;
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

    public AddSubjectDto getType() {
        return type;
    }

    public void setType(AddSubjectDto type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getRemainder() {
        return remainder;
    }

    public void setRemainder(Long remainder) {
        this.remainder = remainder;
    }

    public List<AddTagDto> getTags() {
        return tags;
    }

    public void setTags(List<AddTagDto> tags) {
        this.tags = tags;
    }

    public String getProductSubtype() {
        return productSubtype;
    }

    public void setProductSubtype(String productSubtype) {
        this.productSubtype = productSubtype;
    }

}