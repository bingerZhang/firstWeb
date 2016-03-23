package com.binge.server.model;

import javax.persistence.*;

/**
 * Created by zlb on 2016/3/10.
 */
@Entity
@Table(name = "project_detail", schema = "testweb", catalog = "")
public class ProjectDetailEntity {
    private int id;
    private Integer projectId;
    private String projectName;
    private String imagePath;
    private String description;
    private FamousProjectEntity famousProjectById;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "projectID", nullable = true)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "projectName", nullable = true, length = 128)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "ImagePath", nullable = true, length = 1024)
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 10240)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectDetailEntity that = (ProjectDetailEntity) o;

        if (id != that.id) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (projectName != null ? !projectName.equals(that.projectName) : that.projectName != null) return false;
        if (imagePath != null ? !imagePath.equals(that.imagePath) : that.imagePath != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "projectID", referencedColumnName = "Id")
    public FamousProjectEntity getFamousProjectById() {
        return famousProjectById;
    }

    public void setFamousProjectById(FamousProjectEntity famousProjectById) {
        this.famousProjectById = famousProjectById;
    }
}
