package com.binge.configurator;

import com.binge.configuration.HotImageConfiguration;
import com.binge.module.HotImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zlb on 2016/4/18.
 */
public class HotImageConfigurator extends JsonConfigurator<HotImageConfiguration> {
    public HotImageConfigurator(File file) {
        super(file);
    }

    @Override
    protected HotImageConfiguration getConfiguration(Map<String, Object> properties) throws IOException {
        HotImageConfiguration hotImageConfiguration = new HotImageConfiguration();
        if(properties!=null){
            hotImageConfiguration.setHotImageList(ObjectMapper.getList(properties, "hotimages", new HotImageObjectMapper()));
        } else {
            hotImageConfiguration.setHotImageList(new ArrayList<HotImage>());
        }

        return hotImageConfiguration;

    }

    @Override
    protected Map<String, Object> getProperties(HotImageConfiguration configuration) throws IOException {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hotimages", ObjectMapper.serialize(configuration.getHotImageList(), new HotImageObjectMapper()));

        return properties;
    }

    protected class HotImageObjectMapper extends ObjectMapper<HotImage> {

        @Override
        public Map<String, Object> serialize(HotImage value) {
            Map<String, Object> properteis = new HashMap<String, Object>();
            properteis.put("id", value.getId());
            properteis.put("name", value.getName());
            properteis.put("path", value.getPath());
            properteis.put("active", value.getActive());
            return properteis;
        }

        @Override
        public HotImage deserialize(Map<String, Object> map) {
            HotImage hotImage = new HotImage();
            hotImage.setId(getInt(map, "id", 0));
            hotImage.setName(getString(map, "name"));
            hotImage.setPath(getString(map, "path"));
            hotImage.setActive(getString(map, "active", "true"));

            return hotImage;
        }
    }
}
