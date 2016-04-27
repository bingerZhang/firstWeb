package com.binge.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping( "/company" )
public class CompanyController {
    @RequestMapping( "/introduction" )
    public String introduction() {

        return "/company/introduction";
    }
    @RequestMapping( "/culture" )
    public String culture() {

        return "/company/culture";
    }
}
