package com.binge.configuration;

import com.binge.module.HotImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlb on 2016/4/18.
 */
public class HotImageConfiguration implements Configuration {
    public static final String TYPE = "hotimage";
    private List<HotImage> hotImageList;

    public List<HotImage> getHotImageList() {
        return hotImageList;
    }

    public void setHotImageList(List<HotImage> hotImageList) {
        this.hotImageList = hotImageList;
    }

    public void addHotImage(HotImage hotImage){
        if(hotImageList==null)
            hotImageList = new ArrayList<HotImage>();
        hotImageList.add(hotImage);
    }

    @Override
    public String getType() {
        return TYPE;
    }
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
