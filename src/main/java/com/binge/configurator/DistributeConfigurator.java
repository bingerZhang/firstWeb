package com.binge.configurator;

import com.binge.configuration.DistributeConfiguration;
import com.binge.module.DistributeFile;
import com.binge.module.Distributelist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DistributeConfigurator  extends JsonConfigurator<DistributeConfiguration> {
    public DistributeConfigurator(File file) {
        super(file);
    }

    protected DistributeConfiguration getConfiguration(Map<String, Object> properties) throws IOException {
        DistributeConfiguration configuration = new DistributeConfiguration();

        if (properties != null) {
            configuration.setSequence(JsonConfigurator.ObjectMapper.getInt(properties, "sequence", 0));
            configuration.setDistributelists(ObjectMapper.getList(properties, "distributelists", new DistributelistObjectMapper()));
        } else {
            configuration.setDistributelists(new ArrayList<Distributelist>());
            configuration.setSequence(0);
        }

        return configuration;
    }

    protected Map<String, Object> getProperties(DistributeConfiguration configuration) throws IOException {
        Map<String, Object> properties = new HashMap<String, Object>();

        properties.put("sequence", configuration.getSequence());
        properties.put("distributelists", ObjectMapper.serialize(configuration.getDistributelists(), new DistributelistObjectMapper()));

        return properties;
    }

    protected class DistributelistObjectMapper extends ObjectMapper<Distributelist> {

        @Override
        public Map<String, Object> serialize(Distributelist value) {
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("id", value.getId());
            properties.put("sequence", value.getSequence());
            properties.put("name", value.getName());
            properties.put("distributes", ObjectMapper.serialize(value.getDistributes(), new DistributeObjectMapper()));
            properties.put("networks", value.getNetworks());
            properties.put("createTime", value.getCreateTime());
            properties.put("updateTime",value.getUpdateTime());

            return properties;
        }

        @Override
        public Distributelist deserialize(Map<String, Object> map) {
            Distributelist list = new Distributelist();
            list.setId(getLong(map, "id", 0));
            list.setSequence(getLong(map, "sequence", 0));
            list.setName(getString(map, "name"));
            list.setNetworks(getArray(map, "networks", new String[0]));
            list.setDistributes(ObjectMapper.getList(map, "distributes", new DistributeObjectMapper()));
            list.setCreateTime(getDate(map, "createTime"));
            list.setUpdateTime(getDate(map,"updateTime"));

            return list;
        }
    }

    protected class DistributeObjectMapper extends ObjectMapper<DistributeFile> {

        @Override
        public Map<String, Object> serialize(DistributeFile value) {
            Map<String, Object> properteis = new HashMap<String, Object>();
            properteis.put("id", value.getId());
            properteis.put("name", value.getName());
            properteis.put("md5", value.getMd5Value());
            properteis.put("size",value.getSize());
            properteis.put("sourceUrls", value.getSourceUrls());


            return properteis;
        }

        @Override
        public DistributeFile deserialize(Map<String, Object> map) {
            DistributeFile distribute = new DistributeFile();
            distribute.setId(getLong(map, "id", 0));
            distribute.setName(getString(map, "name"));
            distribute.setMd5Value(getString(map, "md5"));
            distribute.setSize(getLong(map,"size",0));
            distribute.setSourceUrls(getArray(map, "sourceUrls", new String[0]));

            return distribute;
        }
    }
}
