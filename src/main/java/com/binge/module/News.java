package com.binge.module;

import com.binge.util.BeanWrapper;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zlb on 2016/4/18.
 */
public class News implements Serializable,BeanWrapper {
    private int id;
    private String title;
    private String description;
    private String imagepath;
    private String detail;
    private String updatetime;
    private String videourl;

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public News() {
    }

    public News(int id, String title, String description, String imagepath, String detail, String updatetime,String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imagepath = imagepath;
        this.detail = detail;
        this.updatetime = updatetime;
        this.videourl = url;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public boolean equals(Object obj) {
        if(obj instanceof News) {
            return this.id == ((News) obj).getId();
        }
        return false;
    }
}
