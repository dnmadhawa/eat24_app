package com.company.eat24.models;

public class Food {

    String category, furl, name, prize, size;
    public Food()
    {}
    public Food(String category, String furl, String name, String prize, String size) {
        this.category = category;
        this.furl = furl;
        this.name = name;
        this.prize = prize;
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
