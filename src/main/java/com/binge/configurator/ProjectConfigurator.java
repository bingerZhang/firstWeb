package com.binge.configurator;

import com.binge.configuration.ProjectConfiguration;
import com.binge.module.FamousProject;
import com.binge.module.Project;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by zlb on 2016/4/18.
 */
public class ProjectConfigurator extends JsonConfigurator<ProjectConfiguration> {
    public ProjectConfigurator(File file) {
        super(file);
    }

    @Override
    protected ProjectConfiguration getConfiguration(Map<String, Object> properties) throws IOException {
        ProjectConfiguration projectConfiguration = new ProjectConfiguration();
        if(properties!=null){
            projectConfiguration.setFamousProjectList(ObjectMapper.getList(properties, "famousprojects", new FamousProjectObjectMapper()));
            projectConfiguration.setProjectList(ObjectMapper.getList(properties,"projects",new ProjectObjectMapper()));
        } else {
            projectConfiguration.setFamousProjectList(new ArrayList<FamousProject>());
            projectConfiguration.setProjectList(new ArrayList<Project>());
        }

        return projectConfiguration;

    }

    @Override
    protected Map<String, Object> getProperties(ProjectConfiguration configuration) throws IOException {
        Map<String, Object> properties = new LinkedHashMap<String, Object>();
        properties.put("famousprojects", ObjectMapper.serialize(configuration.getFamousProjectList(), new FamousProjectObjectMapper()));
        properties.put("projects", ObjectMapper.serialize(configuration.getProjectList(), new ProjectObjectMapper()));
        return properties;
    }

    protected class FamousProjectObjectMapper extends ObjectMapper<FamousProject> {

        @Override
        public Map<String, Object> serialize(FamousProject value) {
            Map<String, Object> properteis = new HashMap<String, Object>();
            properteis.put("id", value.getId());
            properteis.put("name", value.getName());
            properteis.put("iconpath", value.getIconPath());
            properteis.put("imagepath", value.getImagePath());
            properteis.put("description", value.getDescription());
            return properteis;
        }

        @Override
        public FamousProject deserialize(Map<String, Object> map) {
            FamousProject famousProject = new FamousProject();
            famousProject.setId(getInt(map, "id", 0));
            famousProject.setName(getString(map, "name"));
            famousProject.setIconPath(getString(map, "iconpath"));
            famousProject.setImagePath(new imagePathObjectMapper().deserialize(map));
            famousProject.setDescription(getString(map,"description"));

            return famousProject;
        }
    }

    protected class imagePathObjectMapper extends ObjectMapper<List<String>> {

        @Override
        public Map<String, Object> serialize(List<String> value) {
            Map<String, Object> properteis = new HashMap<String, Object>();
            properteis.put("imagepath", value);
            return properteis;
        }

        @Override
        public List<String> deserialize(Map<String, Object> map) {
            List<String> list = (List<String>) map.get("imagepath");
            return list;
        }
    }

    protected class ProjectObjectMapper extends ObjectMapper<Project> {

        @Override
        public Map<String, Object> serialize(Project value) {
            Map<String, Object> properteis = new HashMap<String, Object>();
            properteis.put("id", value.getId());
            properteis.put("name", value.getName());
            properteis.put("city",value.getCity());
            return properteis;
        }

        @Override
        public Project deserialize(Map<String, Object> map) {
            Project project = new Project();
            project.setId(getInt(map, "id", 0));
            project.setName(getString(map, "name"));
            project.setCity(getInt(map,"city"));
            return project;
        }
    }
}
