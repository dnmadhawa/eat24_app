package com.company.eat24;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class BillingModel  implements Serializable {
    String datetime,  discount,orderid,subtotal,total;
    ArrayList<HashMap> item;

    public BillingModel() {
    }

    public BillingModel(String datetime, String discount, String orderid, String subtotal, String total, ArrayList<HashMap> item) {
        this.datetime = datetime;
        this.discount = discount;
        this.orderid = orderid;
        this.subtotal = subtotal;
        this.total = total;
        this.item = item;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<HashMap> getItem() {
        return item;
    }

    public void setItem(ArrayList<HashMap> item) {
        this.item = item;
    }
}
