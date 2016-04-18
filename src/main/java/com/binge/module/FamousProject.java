package com.binge.module;

import com.binge.util.BeanWrapper;

import java.io.Serializable;

/**
 * Created by zlb on 2016/4/18.
 */
public class FamousProject implements Serializable,BeanWrapper {
    private int id;
    private String name;
    private String iconPath;
    private String imagePath;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
