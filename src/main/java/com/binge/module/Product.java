package com.binge.module;

import com.binge.util.BeanWrapper;

import java.io.Serializable;

/**
 * Created by zlb on 2016/4/18.
 */
public class Product implements Serializable,BeanWrapper {
    private int id;
    private String name;
    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
