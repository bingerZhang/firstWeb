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
public class WelcomeController {
    @RequestMapping( "/indexa" )
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( "index" );
        modelAndView.addObject( " 需要放到 model 中的属性名称 " , " 对应的属性值，它是一个对象 " );
        return modelAndView;
    }

    @RequestMapping( "/login" )
    public ModelAndView login() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( "welcome/login" );
        modelAndView.addObject( "message" , " 对应的属性值，它是一个对象 " );
        return modelAndView;
    }

    @RequestMapping( "/login2" )
    public String login2(ModelMap model) {
//        ModelMap model=new ModelMap();
        model.addAttribute("message"," 对应的属性值，它是一个对象 ");
        return "/index";
    }
}
