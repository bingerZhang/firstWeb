package com.binge.configuration;

import com.binge.module.Brand;
import com.binge.module.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlb on 2016/4/18.
 */
public class ContactConfiguration implements Configuration {
    public static final String TYPE = "contact";
    private List<Contact> contact;

    public ContactConfiguration() {
        this.contact = new ArrayList<Contact>();
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
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
