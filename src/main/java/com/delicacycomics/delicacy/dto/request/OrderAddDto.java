package com.delicacycomics.delicacy.dto.request;

// todo: correct this DTO class
public class OrderAddDto {

    private Long orderId;
    private Long productId;
    private Integer amount;

    public OrderAddDto() { }
    public Long getProductId() {
        return productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderAddDto{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", amount=" + amount +
                '}';
    }
}
