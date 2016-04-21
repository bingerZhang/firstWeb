package com.binge.configuration;

import com.binge.module.FamousProject;
import com.binge.module.Product;
import com.binge.module.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlb on 2016/4/18.
 */
public class ProductConfiguration implements Configuration {
    public static final String TYPE = "product";
    private List<Product> productsList;

    public List<Product> getProductsList() {
        if(productsList == null) productsList = new ArrayList<Product>();
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    public void addProduct(Product product){
        if(productsList==null)
            productsList = new ArrayList<Product>();
        productsList.add(product);
    }

    public List<Product> getProductsListByType(int type){
        List<Product> list = new ArrayList<Product>();
        for(Product product: productsList){
            if(product.getType()==type)
            {
                list.add(product);
            }
        }
        return list;
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
