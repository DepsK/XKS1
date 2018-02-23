package com.dream.xukuan.stu8.bean;

/**
 * @author XK
 * @date 2018/2/23.
 */
public class CityEntity {
    public String imageUrl;
    public String name;
    public String address;
    public String price_pre;
    public String price_value;
    public String price_unit;

    @Override
    public String toString() {
        return "CityEntity{" +
                "imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", price_pre='" + price_pre + '\'' +
                ", price_value='" + price_value + '\'' +
                ", price_unit='" + price_unit + '\'' +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice_pre() {
        return price_pre;
    }

    public void setPrice_pre(String price_pre) {
        this.price_pre = price_pre;
    }


    public String getPrice_value() {
        return price_value;
    }

    public void setPrice_value(String price_value) {
        this.price_value = price_value;
    }

    public String getPrice_unit() {
        return price_unit;
    }

    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public CityEntity(String imageUrl, String name, String address, String price_pre, String price_value, String price_unit) {

        this.imageUrl = imageUrl;
        this.name = name;
        this.address = address;
        this.price_pre = price_pre;
        this.price_value = price_value;
        this.price_unit = price_unit;
    }

}