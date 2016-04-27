package com.binge.configurator;

import com.binge.configuration.BrandConfiguration;
import com.binge.configuration.ProjectConfiguration;
import com.binge.module.Brand;
import com.binge.module.FamousProject;
import com.binge.module.Project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zlb on 2016/4/18.
 */
public class BrandConfigurator extends JsonConfigurator<BrandConfiguration> {
    public BrandConfigurator(File file) {
        super(file);
    }

    @Override
    protected BrandConfiguration getConfiguration(Map<String, Object> properties) throws IOException {
        BrandConfiguration brandConfiguration = new BrandConfiguration();
        if(properties!=null){
            brandConfiguration.setProductionEquipmentList(ObjectMapper.getList(properties, "productionequipments", new BrandObjectMapper()));
            brandConfiguration.setMineralInformationList(ObjectMapper.getList(properties, "mineralInfos", new BrandObjectMapper()));
            brandConfiguration.setFactoryPicList(ObjectMapper.getList(properties, "factorypics", new BrandObjectMapper()));

        } else {
            brandConfiguration.setProductionEquipmentList(new ArrayList<Brand>());
            brandConfiguration.setMineralInformationList(new ArrayList<Brand>());
            brandConfiguration.setFactoryPicList(new ArrayList<Brand>());

        }

        return brandConfiguration;

    }

    @Override
    protected Map<String, Object> getProperties(BrandConfiguration configuration) throws IOException {
        Map<String, Object> properties = new LinkedHashMap<String, Object>();
        properties.put("factorypics", ObjectMapper.serialize(configuration.getFactoryPicList(), new BrandObjectMapper()));
        properties.put("mineralInfos", ObjectMapper.serialize(configuration.getMineralInformationList(), new BrandObjectMapper()));
        properties.put("productionequipments", ObjectMapper.serialize(configuration.getProductionEquipmentList(), new BrandObjectMapper()));

        return properties;
    }

    protected class BrandObjectMapper extends ObjectMapper<Brand> {

        @Override
        public Map<String, Object> serialize(Brand value) {
            Map<String, Object> properteis = new HashMap<String, Object>();
            properteis.put("id", value.getId());
            properteis.put("brandType", value.getBrandType());
            properteis.put("imagepath", value.getImagePath());
            properteis.put("description", value.getDescription());
            return properteis;
        }

        @Override
        public Brand deserialize(Map<String, Object> map) {
            Brand brand = new Brand();
            brand.setId(getInt(map, "id", 0));
            brand.setBrandType(getInt(map, "brandType"));
            brand.setImagePath(getString(map, "imagepath"));
            brand.setDescription(getString(map,"description"));

            return brand;
        }
    }

}
