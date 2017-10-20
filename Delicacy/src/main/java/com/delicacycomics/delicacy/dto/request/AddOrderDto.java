package com.delicacycomics.delicacy.dto.request;

//{
//    "userId": 14,
//    "note": "Note"
//}

public class AddOrderDto {

    private Long userId;
    private String note;

    public AddOrderDto() { }

    public AddOrderDto(Long userId, String note) {
        this.userId = userId;
        this.note = note;
    }

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
