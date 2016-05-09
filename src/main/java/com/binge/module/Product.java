package com.binge.module;

import com.binge.util.BeanWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zlb on 2016/4/18.
 */
public class Product implements Serializable,BeanWrapper {
    private int id;
    private String name;
    private int type;  //city / park / water /architecture / Tombstone / technology
    private String description;
    private List<String> imagepath;
    private Date createtime;

    public Product() {
    }

    public Product(int id, String name, int type, String description, List<String> imagepath, Date createtime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.imagepath = imagepath;
        this.createtime = createtime;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImagepath() {
        if(imagepath==null)imagepath = new ArrayList<String>();
        return imagepath;
    }

    public void setImagepath(List<String> imagepath) {
        this.imagepath = imagepath;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
