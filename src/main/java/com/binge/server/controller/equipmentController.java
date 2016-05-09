package com.binge.server.controller;

import com.binge.configuration.BrandConfiguration;
import com.binge.configuration.HotImageConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: zlb
 * Date: 16-3-9
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping( "/equipment" )
public class EquipmentController extends DefaultController {
    @RequestMapping( "/EquipmentList" )
    public String equipments() {
        BrandConfiguration configuration = getConfiguration(BrandConfiguration.class);
        request.setAttribute("type",1);
        request.setAttribute("equipments", configuration.getProductionEquipmentList());
        return "/equipment/equipment";
    }
    @RequestMapping( "/mineralInformationList" )
    public String mineralInformations() {
        BrandConfiguration configuration = getConfiguration(BrandConfiguration.class);
        request.setAttribute("type",2);
        request.setAttribute("equipments", configuration.getMineralInformationList());
        return "/equipment/equipment";
    }
    @RequestMapping( "/factoryPicList" )
    public String factoryPics() {
        BrandConfiguration configuration = getConfiguration(BrandConfiguration.class);
        request.setAttribute("type",3);
        request.setAttribute("equipments", configuration.getFactoryPicList());
        return "/equipment/equipment";
    }
}
