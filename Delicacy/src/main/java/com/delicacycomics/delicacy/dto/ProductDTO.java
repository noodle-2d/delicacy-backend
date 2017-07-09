package com.delicacycomics.delicacy.dto;

public class ProductDTO {

    private Long id;
    private String title;

    protected ProductDTO() { }

    public ProductDTO(Long id, String title) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDTO that = (ProductDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return title != null ? title.equals(that.title) : that.title == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

}