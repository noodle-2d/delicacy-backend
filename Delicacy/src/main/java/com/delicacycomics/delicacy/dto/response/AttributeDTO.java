package com.delicacycomics.delicacy.dto.response;

public class AttributeDTO extends ProductDTO {

    private Long height;
    private SubjectDTO manufacturer;
    private String series;
    private String material;

    public AttributeDTO() {
        super(ProductDTO.ATTRIBUTE_SUBTYPE);
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public SubjectDTO getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(SubjectDTO manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

}
