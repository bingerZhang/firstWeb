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
    private List<Brand> productionEquipmentList;
    private List<Brand> mineralInformationList;
    private List<Brand> factoryPicList;

    public BrandConfiguration() {
        this.productionEquipmentList = new ArrayList<Brand>();
        this.mineralInformationList = new ArrayList<Brand>();
        this.factoryPicList = new ArrayList<Brand>();
    }

    public List<Brand> getProductionEquipmentList() {
        if(productionEquipmentList==null){
            productionEquipmentList = new ArrayList<Brand>();
        }
        return productionEquipmentList;
    }

    public void setProductionEquipmentList(List<Brand> productionEquipmentList) {
        this.productionEquipmentList = productionEquipmentList;
    }

    public List<Brand> getMineralInformationList() {
        if(mineralInformationList==null){
            mineralInformationList = new ArrayList<Brand>();
        }
        return mineralInformationList;
    }

    public void setMineralInformationList(List<Brand> mineralInformationList) {
        this.mineralInformationList = mineralInformationList;
    }

    public List<Brand> getFactoryPicList() {
        if(factoryPicList==null){
            factoryPicList = new ArrayList<Brand>();
        }
        return factoryPicList;
    }

    public void setFactoryPicList(List<Brand> factoryPicList) {
        this.factoryPicList = factoryPicList;
    }

    public void addBrand(String brandtype,Brand brand){
        if(brandtype==null)return;
        if (brandtype.equals("factoryPic")) {
            factoryPicList.add(brand);
        }else if(brandtype.equals("mineralInformation")){
            mineralInformationList.add(brand);
        }if(brandtype.equals("productionEquipment")){
            productionEquipmentList.add(brand);
        }
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
