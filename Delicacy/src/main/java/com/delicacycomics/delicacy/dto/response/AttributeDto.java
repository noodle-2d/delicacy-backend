package com.delicacycomics.delicacy.dto.response;

public class AttributeDto extends ProductDto {

    private Long height;
    private SubjectDto manufacturer;
    private String series;
    private String material;

    public AttributeDto() {
        super(ProductDto.ATTRIBUTE_SUBTYPE);
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public SubjectDto getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(SubjectDto manufacturer) {
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
