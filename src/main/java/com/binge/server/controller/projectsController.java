package com.binge.server.controller;

import com.binge.configuration.ProjectConfiguration;
import com.binge.module.FamousProject;
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
@RequestMapping( "/projects/" )
public class ProjectsController extends DefaultController{
    private ProjectConfiguration getConfiguration() {
        return getConfiguration(ProjectConfiguration.class);
    }

    @RequestMapping( "/cases" )
    public String cases() {
        ProjectConfiguration configuration = getConfiguration();
        request.setAttribute("type",1);
        request.setAttribute("famousprojects", configuration.getFamousProjectList());
        return "/projects/cases";
    }
    @RequestMapping( "/lists" )
    public String lists() {
        ProjectConfiguration configuration = getConfiguration();
        request.setAttribute("type",2);
        request.setAttribute("projects", configuration.getProjectList());

        return "/projects/lists";
    }
    @RequestMapping( "/detail" )
    public String detail() {
        ProjectConfiguration configuration = getConfiguration();
        int id = 0;
        String idstr = request.getParameter("id");
        if (idstr != null && idstr.length() > 0) {
            id = Integer.valueOf(idstr);
        }
        FamousProject famousProject = configuration.getFamousProjectById(id);
        if (famousProject != null)
        {
            request.setAttribute("famousproject", famousProject);
        }
        return "projects/detail";
    }

}
