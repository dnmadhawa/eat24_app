package com.company.eat24.models;

public class DeliveryItem {

    private String iname;
    private String iqty;
    private String iprice;
    private String istatus;

    public DeliveryItem() {
    }

    public DeliveryItem(String iname, String iqty, String iprice, String istatus) {
        this.iname = iname;
        this.iqty = iqty;
        this.iprice = iprice;
        this.istatus = istatus;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIqty() {
        return iqty;
    }

    public void setIqty(String iqty) {
        this.iqty = iqty;
    }

    public String getIprice() {
        return iprice;
    }

    public void setIprice(String iprice) {
        this.iprice = iprice;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }
}
