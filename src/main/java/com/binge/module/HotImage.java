package com.binge.module;

import com.binge.util.BeanWrapper;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by zlb on 2016/4/18.
 */
public class HotImage implements Serializable,BeanWrapper {
    private int id;
    private String name;
    private String path;
    private boolean active;


    public String getActive() {
        if (isActive())
        {
            return "true";
        }else{
            return "false";
        }
    }

    public void setActive(String active)
    {
        if(active.equals("true")){
            setActive(true);
        }else{
            setActive(false);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
