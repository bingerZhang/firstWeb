package com.binge.configuration;

import com.binge.module.FamousProject;
import com.binge.module.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlb on 2016/4/18.
 */
public class ProjectConfiguration implements Configuration {
    public static final String TYPE = "project";
    private List<FamousProject> famousProjectList;
    private List<Project> projectList;

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<FamousProject> getFamousProjectList() {
        if(famousProjectList==null){
            famousProjectList = new ArrayList<FamousProject>();
        }
        return famousProjectList;
    }

    public void setFamousProjectList(List<FamousProject> FamousProjectList) {
        this.famousProjectList = FamousProjectList;
    }

    public void addFamousProject(FamousProject FamousProject){
        if(famousProjectList==null)
            famousProjectList = new ArrayList<FamousProject>();
        famousProjectList.add(FamousProject);
    }

    public FamousProject getFamousProjectById(int id){
        for(FamousProject famousProject:famousProjectList){
            if(famousProject.getId()==id)
                return famousProject;
        }
        return null;
    }

    public void addProject(Project project){
        if(projectList==null)
            projectList = new ArrayList<Project>();
        projectList.add(project);
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
