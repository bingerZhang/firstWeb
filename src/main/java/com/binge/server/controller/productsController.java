package com.binge.server.controller;

import com.binge.configuration.ProductConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: zlb
 * Date: 16-3-9
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping( "/products" )
public class ProductsController extends DefaultController{
    private ProductConfiguration getConfiguration() {
        return getConfiguration(ProductConfiguration.class);
    }
    @RequestMapping( "/city" )
    public String getCityProducts() {
        ProductConfiguration productConfiguration = getConfiguration();
        request.setAttribute("productlists", productConfiguration.getProductsListByType(1));
        request.setAttribute("type",1);
        return "products/lists";
    }
    @RequestMapping( "/garden" )
    public String getGardenProducts() {
        ProductConfiguration productConfiguration = getConfiguration();
        request.setAttribute("productlists", productConfiguration.getProductsListByType(2));
        request.setAttribute("type",2);
        return "products/lists";
    }
    @RequestMapping( "/water" )
    public String getWaterProducts() {
        ProductConfiguration productConfiguration = getConfiguration();
        request.setAttribute("productlists", productConfiguration.getProductsListByType(3));
        request.setAttribute("type",3);
        return "products/lists";
    }
    @RequestMapping( "/architecture" )
    public String getArchitectureProducts() {
        ProductConfiguration productConfiguration = getConfiguration();
        request.setAttribute("productlists", productConfiguration.getProductsListByType(4));
        request.setAttribute("type",4);
        return "products/lists";
    }
    @RequestMapping( "/monument" )
    public String getMonumentProducts() {
        ProductConfiguration productConfiguration = getConfiguration();
        request.setAttribute("productlists", productConfiguration.getProductsListByType(5));
        request.setAttribute("type",5);
        return "products/lists";
    }
    @RequestMapping( "/technology" )
    public String getTechnologyProducts() {
        ProductConfiguration productConfiguration = getConfiguration();
        request.setAttribute("productlists", productConfiguration.getProductsListByType(6));
        request.setAttribute("type",6);
        return "products/lists";
    }

    @RequestMapping( "/detail" )
    public String detail() {
        ProductConfiguration productConfiguration = getConfiguration();

        return "products/detail";
    }
}
