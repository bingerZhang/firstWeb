package com.binge.configurator;

import com.binge.configuration.ContactConfiguration;
import com.binge.module.Brand;
import com.binge.module.Contact;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zlb on 2016/4/18.
 */
public class ContactConfigurator extends JsonConfigurator<ContactConfiguration> {
    public ContactConfigurator(File file) {
        super(file);
    }

    @Override
    protected ContactConfiguration getConfiguration(Map<String, Object> properties) throws IOException {
        ContactConfiguration contactConfiguration = new ContactConfiguration();
        if(properties!=null){
            contactConfiguration.setContact(ObjectMapper.getList(properties, "contact", new ContactObjectMapper()));
        } else {
            contactConfiguration.setContact(new ArrayList<Contact>());
        }

        return contactConfiguration;

    }

    @Override
    protected Map<String, Object> getProperties(ContactConfiguration configuration) throws IOException {
        Map<String, Object> properties = new LinkedHashMap<String, Object>();
        ContactObjectMapper contactObjectMapper = new ContactObjectMapper();
        properties.put("contact",  ObjectMapper.serialize(configuration.getContact(), new ContactObjectMapper()));
        return properties;
    }

    protected class ContactObjectMapper extends ObjectMapper<Contact> {

        @Override
        public Map<String, Object> serialize(Contact value) {
            Map<String, Object> properteis = new HashMap<String, Object>();
            properteis.put("id", value.getId());
            properteis.put("name", value.getName());
            properteis.put("address", value.getAddress());
            properteis.put("companyname", value.getCompanyname());
            properteis.put("fax", value.getFax());
            properteis.put("phone", value.getPhone());
            return properteis;
        }

        @Override
        public Contact deserialize(Map<String, Object> map) {
            Contact contact = new Contact();
            contact.setId(getInt(map, "id", 0));
            contact.setName(getString(map, "name"));
            contact.setAddress(getString(map, "address"));
            contact.setCompanyname(getString(map,"companyname"));
            contact.setFax(getString(map,"fax"));
            contact.setPhone(getString(map,"phone"));
            return contact;
        }
    }

}
