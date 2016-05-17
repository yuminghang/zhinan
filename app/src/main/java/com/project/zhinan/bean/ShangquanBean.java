package com.project.zhinan.bean;

/**
 * Created by deng on 16-5-17.
 */
public class ShangquanBean {
    String id;
    String imgUrl;
    String location;
    String storeName;

    public ShangquanBean(String imgUrl, String location, String storeName) {
        this.imgUrl = imgUrl;
        this.location = location;
        this.storeName = storeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
