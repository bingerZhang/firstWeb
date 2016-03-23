package com.binge.server.controller;

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
@RequestMapping( "/projects/" )
public class projectsController {
    @RequestMapping( "/cases" )
    public String cases() {

        return "/projects/cases";
    }
    @RequestMapping( "/lists" )
    public String lists() {

        return "/projects/lists";
    }
}
