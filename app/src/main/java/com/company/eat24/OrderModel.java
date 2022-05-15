package com.company.eat24;

public class OrderModel {
    int deleteImage;
    String orderNum,item,itemQuant,orderStatus;

    public OrderModel(int deleteImage, String orderNum, String item, String itemQuant, String orderStatus) {
        this.deleteImage = deleteImage;
        this.orderNum = orderNum;
        this.item = item;
        this.itemQuant = itemQuant;
        this.orderStatus = orderStatus;
    }

    public int getDeleteImage() {
        return deleteImage;
    }

    public void setDeleteImage(int deleteImage) {
        this.deleteImage = deleteImage;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemQuant() {
        return itemQuant;
    }

    public void setItemQuant(String itemQuant) {
        this.itemQuant = itemQuant;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
