package com.binge.configurator;

import com.binge.configuration.ProductConfiguration;
import com.binge.configuration.ProjectConfiguration;
import com.binge.module.FamousProject;
import com.binge.module.Product;
import com.binge.module.Project;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by zlb on 2016/4/18.
 */
public class ProductConfigurator extends JsonConfigurator<ProductConfiguration> {
    public ProductConfigurator(File file) {
        super(file);
    }

    @Override
    protected ProductConfiguration getConfiguration(Map<String, Object> properties) throws IOException {
        ProductConfiguration productConfiguration = new ProductConfiguration();
        if(properties!=null){
            productConfiguration.setProductsList(ObjectMapper.getList(properties, "products", new ProductObjectMapper()));

        } else {
            productConfiguration.setProductsList(new ArrayList<Product>());
        }

        return productConfiguration;

    }

    @Override
    protected Map<String, Object> getProperties(ProductConfiguration configuration) throws IOException {
        Map<String, Object> properties = new LinkedHashMap<String, Object>();
        properties.put("products", ObjectMapper.serialize(configuration.getProductsList(), new ProductObjectMapper()));
        return properties;
    }

    protected class ProductObjectMapper extends ObjectMapper<Product> {

        @Override
        public Map<String, Object> serialize(Product value) {
            Map<String, Object> properteis = new HashMap<String, Object>();
            properteis.put("id", value.getId());
            properteis.put("name", value.getName());
            properteis.put("type",value.getType());
            properteis.put("description",value.getDescription());
            properteis.put("imagepaths",value.getImagepath());
            properteis.put("createtime",value.getCreatetime());
            return properteis;
        }

        @Override
        public Product deserialize(Map<String, Object> map) {
            Product product = new Product();
            product.setId(getInt(map, "id", 0));
            product.setName(getString(map, "name"));
            product.setType(getInt(map, "type"));
            product.setDescription(getString(map, "description"));
            product.setImagepath(Arrays.asList(getArray(map, "imagepaths",new String[0])));
            product.setCreatetime(getDate(map, "createtime"));
            return product;
        }
    }
}
