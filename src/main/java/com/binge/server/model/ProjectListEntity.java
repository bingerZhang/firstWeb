package com.binge.server.model;

import javax.persistence.*;

/**
 * Created by zlb on 2016/3/10.
 */
@Entity
@Table(name = "project_list", schema = "testweb", catalog = "")
public class ProjectListEntity {
    private int id;
    private Integer cityId;
    private String projectName;
    private CitysEntity cityBycityId;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cityId", nullable = true)
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "ProjectName", nullable = true, length = 128)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectListEntity that = (ProjectListEntity) o;

        if (id != that.id) return false;
        if (cityId != null ? !cityId.equals(that.cityId) : that.cityId != null) return false;
        if (projectName != null ? !projectName.equals(that.projectName) : that.projectName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cityId", referencedColumnName = "Id")
    public CitysEntity getCityBycityId() {
        return cityBycityId;
    }

    public void setCityBycityId(CitysEntity cityBycityId) {
        this.cityBycityId = cityBycityId;
    }
}
