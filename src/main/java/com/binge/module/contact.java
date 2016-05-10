package com.binge.module;

import com.binge.util.BeanWrapper;

import java.io.Serializable;

/**
 * Created by zlb on 2016/4/18.
 */
public class Contact implements Serializable,BeanWrapper {
    private int id;
    private String companyname ;
    private String name;
    private String address;
    private String phone;
    private String fax;

    public Contact(int id, String name, String companyname, String address, String phone, String fax) {
        this.id = id;
        this.name = name;
        this.companyname = companyname;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
    }

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

}
