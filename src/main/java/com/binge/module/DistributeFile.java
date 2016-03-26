package com.binge.module;

import com.binge.util.BeanWrapper;

public class DistributeFile implements BeanWrapper {
    private long id;
    private String name;
    private String md5Value;
    private long size;
    private String[] sourceUrls;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMd5Value() {
        return md5Value;
    }

    public void setMd5Value(String md5Value) {
        this.md5Value = md5Value;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String[] getSourceUrls() {
        return sourceUrls;
    }

    public void setSourceUrls(String[] sourceUrls) {
        this.sourceUrls = sourceUrls;
    }
}
