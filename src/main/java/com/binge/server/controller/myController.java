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
public class MyController {
    @RequestMapping( "/" )
    public String welcome() {

        return "index";
    }

    @RequestMapping( "/index" )
    public String index() {

        return "index";
    }
    @RequestMapping( "/foot" )
    public String foot() {

        return "/foot";
    }
    @RequestMapping( "/scott" )
    public String scott() {

        return "/scott";
    }
    @RequestMapping( "/header" )
    public String header() {

        return "/header";
    }

}
