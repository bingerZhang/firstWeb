package com.binge.configuration;


import com.binge.module.DistributeFile;
import com.binge.module.Distributelist;
import com.binge.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DistributeConfiguration implements Configuration {
    public static final String TYPE = "distribute";
    private long sequence;
    private List<Distributelist> distributelists;

    @Override
    public String getType() {
        return TYPE;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public List<Distributelist> getDistributelists() {
        return distributelists;
    }

    public void setDistributelists(List<Distributelist> distributelists) {
        this.distributelists = distributelists;
    }

    public Distributelist getDistributelistById(long id) {
        if (distributelists == null || distributelists.size() < 1) {
            return null;
        }

        for (Distributelist list : distributelists) {
            if (list.getId() == id) {
                return list;
            }
        }

        return null;
    }

    public long addDistributelist(Distributelist distributelist) {
        this.sequence++;
        distributelist.setId(this.sequence);

        if (distributelists == null) {
            distributelists = new ArrayList<Distributelist>();
        }

        distributelists.add(distributelist);

        return this.sequence;
    }

    public Distributelist deleteDistributelistById(long id) {
        if (distributelists == null || distributelists.size() < 1) {
            return null;
        }

        for (int index = 0; index < distributelists.size(); index++) {
            if (distributelists.get(index).getId() == id) {
                return distributelists.remove(index);
            }
        }

        return null;
    }

    public List<DistributeFile> deleteDistributeFiles(Long listId, Long[] ids) {
        List<DistributeFile> distributeFiles = getDistributelistById(listId).getDistributes();
        Iterator<DistributeFile> iterator = distributeFiles.iterator();
        while (iterator.hasNext()) {
            DistributeFile file = iterator.next();
            if (ArrayUtils.contains(ids, file.getId())) {
                iterator.remove();
            }
        }
        return distributeFiles;
    }

    public Object clone() {
        try {
            DistributeConfiguration that = (DistributeConfiguration) super.clone();
            that.distributelists = new ArrayList<Distributelist>(distributelists);
            return that;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
}
