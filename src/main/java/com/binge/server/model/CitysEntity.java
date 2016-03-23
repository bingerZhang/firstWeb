package com.binge.server.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by zlb on 2016/3/10.
 */
@Entity
@Table(name = "citys", schema = "testweb", catalog = "")
public class CitysEntity {
    private int id;
    private String cityName;
    private Collection<ProjectListEntity> projectsByid;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CityName", nullable = true, length = 64)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitysEntity that = (CitysEntity) o;

        if (id != that.id) return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "cityBycityId")
    public Collection<ProjectListEntity> getProjectsByid() {
        return projectsByid;
    }

    public void setProjectsByid(Collection<ProjectListEntity> projectsByid) {
        this.projectsByid = projectsByid;
    }
}
