package com.binge.server.model;

import javax.persistence.*;

/**
 * Created by zlb on 2016/3/10.
 */
@Entity
@Table(name = "famous_project", schema = "testweb", catalog = "")
public class FamousProjectEntity {
    private int id;
    private String name;
    private String iconImagepath;
    private ProjectDetailEntity detailById;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "iconImagepath", nullable = true, length = 1024)
    public String getIconImagepath() {
        return iconImagepath;
    }

    public void setIconImagepath(String iconImagepath) {
        this.iconImagepath = iconImagepath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FamousProjectEntity that = (FamousProjectEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (iconImagepath != null ? !iconImagepath.equals(that.iconImagepath) : that.iconImagepath != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (iconImagepath != null ? iconImagepath.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "famousProjectById")
    public ProjectDetailEntity getDetailById() {
        return detailById;
    }

    public void setDetailById(ProjectDetailEntity detailById) {
        this.detailById = detailById;
    }
}
