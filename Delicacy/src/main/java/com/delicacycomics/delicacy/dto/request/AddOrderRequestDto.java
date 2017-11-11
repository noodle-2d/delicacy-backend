package com.delicacycomics.delicacy.dto.request;

// todo: correct this DTO class
public class AddOrderRequestDto {

    private Long userId;
    private String note;

    public AddOrderRequestDto() { }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
