package com.binge.configuration;

import com.binge.module.Brand;
import com.binge.module.FamousProject;
import com.binge.module.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlb on 2016/4/18.
 */
public class BrandConfiguration implements Configuration {
    public static final String TYPE = "brand";
    private List<Brand> brandList;

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public void addBrand(Brand brand){
        if(brandList==null)
            brandList = new ArrayList<Brand>();
        brandList.add(brand);
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
