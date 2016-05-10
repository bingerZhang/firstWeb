package com.binge.server.controller;

import com.binge.configuration.ContactConfiguration;
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
@RequestMapping( "/contactinfo" )
public class ContactInfoController extends DefaultController{
    @RequestMapping( "/contact" )
    public String contactInfo() {
        ContactConfiguration contactConfiguration = getConfiguration(ContactConfiguration.class);

        request.setAttribute("contact",contactConfiguration.getContact());
        return "contactinfo/contact";
    }

}
