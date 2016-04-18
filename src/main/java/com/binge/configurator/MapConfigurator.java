package com.binge.configurator;

import com.binge.configuration.MapConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zlb on 2016/4/18.
 */
public class MapConfigurator extends JsonConfigurator<MapConfiguration> {

    public MapConfigurator(File file) {
        super(file);
    }

    @Override
    protected MapConfiguration getConfiguration(Map<String, Object> properties) throws IOException {
        MapConfiguration mapConfiguration = new MapConfiguration();
        if(properties!=null){
            mapConfiguration.setCityMap((Map<String, Object>) properties.get("city"));
            mapConfiguration.setCityMap((Map<String, Object>) properties.get("brandtype"));
            mapConfiguration.setCityMap((Map<String, Object>) properties.get("type"));
        }else{
            mapConfiguration.setCityMap(new HashMap<String, Object>());
            mapConfiguration.setBrandtypeMap(new HashMap<String, Object>());
            mapConfiguration.setTypeMap(new HashMap<String, Object>());
        }

        return mapConfiguration;

    }

    @Override
    protected Map<String, Object> getProperties(MapConfiguration configuration) throws IOException {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("citys", configuration.getCityMap());
        properties.put("types", configuration.getTypeMap());
        properties.put("brandtypes", configuration.getBrandtypeMap());
        return properties;
    }

}
