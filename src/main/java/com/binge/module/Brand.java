package com.binge.module;

import com.binge.util.BeanWrapper;

import java.io.Serializable;

/**
 * Created by zlb on 2016/4/18.
 */
public class Brand implements Serializable,BeanWrapper {
    private int id;
    private int brandType;
    private String description;
    private String imagePath;

    public Brand() {
    }

    public Brand(int id, int brandType, String description, String imagePath) {
        this.id = id;
        this.brandType = brandType;
        this.description = description;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandType() {
        return brandType;
    }

    public void setBrandType(int brandType) {
        this.brandType = brandType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
