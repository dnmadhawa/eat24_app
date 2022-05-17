package com.company.eat24;

public class OrderModel {

    String orderID, item, quantity, status, tableNo,price;

    public OrderModel() {
    }

    public OrderModel(String orderID, String item, String quantity, String status, String tableNo, String price) {
        this.orderID = orderID;
        this.item = item;
        this.quantity = quantity;
        this.status = status;
        this.tableNo = tableNo;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }
}