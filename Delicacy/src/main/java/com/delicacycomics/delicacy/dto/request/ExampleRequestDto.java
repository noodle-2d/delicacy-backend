package com.delicacycomics.delicacy.dto.request;

public class ExampleRequestDto {

    private String requestStringField;
    private int requestIntegerField;

    public String getRequestStringField() {
        return requestStringField;
    }

    public void setRequestStringField(String requestStringField) {
        this.requestStringField = requestStringField;
    }

    public int getRequestIntegerField() {
        return requestIntegerField;
    }

    public void setRequestIntegerField(int requestIntegerField) {
        this.requestIntegerField = requestIntegerField;
    }

    @Override
    public String toString() {
        return "ExampleRequestDto{" +
                "requestStringField='" + requestStringField + '\'' +
                ", requestIntegerField=" + requestIntegerField +
                '}';
    }

}
