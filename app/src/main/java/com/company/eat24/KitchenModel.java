package com.company.eat24;

public class KitchenModel {
    int dishImage;
    String orderNumber;
    String itemQuantity;
    String itemName;
    String status;


    public KitchenModel(int dishImage, String orderNumber, String itemQuantity, String itemName, String status) {
        this.dishImage = dishImage;
        this.orderNumber = orderNumber;
        this.itemQuantity = itemQuantity;
        this.itemName = itemName;
        this.status = status;
    }

    public int getDishImage() {
        return dishImage;
    }

    public void setDishImage(int dishImage) {
        this.dishImage = dishImage;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
