package com.binge.server.controller;

import com.binge.configuration.ProjectConfiguration;
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
public class ProjectsController extends DefaultController{
    private ProjectConfiguration getConfiguration() {
        return getConfiguration(ProjectConfiguration.class);
    }

    @RequestMapping( "/cases" )
    public String cases() {
        ProjectConfiguration configuration = getConfiguration();
        request.setAttribute("famousprojects", configuration.getFamousProjectList());
        return "/projects/cases";
    }
    @RequestMapping( "/lists" )
    public String lists() {
        ProjectConfiguration configuration = getConfiguration();
        request.setAttribute("projects", configuration.getProjectList());


        return "/projects/lists";
    }
}
