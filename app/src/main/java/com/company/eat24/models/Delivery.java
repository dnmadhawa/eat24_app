package com.company.eat24.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Delivery implements Serializable
{
    private String d_id;
    private String datetime;
    private String address;
    private String cusName;
    private String delivery_fee;
    private String delivery_status;
    private String mobile_number;
    private String note;
    private String total_amount;
    private ArrayList<HashMap> items;

//    public static int[] did ;
//    public static String[] tamount;
//    public static String[] status;
//    public static String[] datetime ;


    public Delivery() {
    }

    public Delivery(String d_id, String datetime, String address, String cusName, String delivery_fee, String delivery_status, String mobile_number, String note, String total_amount, ArrayList<HashMap> items) {
        this.d_id = d_id;
        this.datetime = datetime;
        this.address = address;
        this.cusName = cusName;
        this.delivery_fee = delivery_fee;
        this.delivery_status = delivery_status;
        this.mobile_number = mobile_number;
        this.note = note;
        this.total_amount = total_amount;
        this.items = items;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(String delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public ArrayList<HashMap> getItems() {
        return items;
    }

    public void setItems(ArrayList<HashMap> items) {
        this.items = items;
    }
}
