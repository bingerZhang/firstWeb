package com.binge.module;

import com.binge.util.BeanWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Distributelist implements BeanWrapper {
    private long id;
    private long sequence;
    private String name;
    private List<DistributeFile> distributes;
    private String[] networks;
    private Date createTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DistributeFile> getDistributes() {
        return distributes;
    }

    public void setDistributes(List<DistributeFile> distributes) {
        this.distributes = distributes;
    }

    public String[] getNetworks() {
        return networks;
    }

    public void setNetworks(String[] networks) {
        this.networks = networks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Distributelist() {
    }

    public Distributelist(long id) {
        this.id = id;
    }

    public DistributeFile getDistributeFileById(long id) {
        if (distributes == null || distributes.size() < 1) {
            return null;
        }

        for (DistributeFile file : distributes) {
            if (file.getId() == id) {
                return file;
            }
        }

        return null;
    }

    public long addDistributeFile(DistributeFile file) {
        this.sequence++;
        file.setId(this.sequence);

        if (this.distributes == null) {
            this.distributes = new ArrayList<DistributeFile>();
        }

        this.distributes.add(file);

        return this.sequence;
    }
}
