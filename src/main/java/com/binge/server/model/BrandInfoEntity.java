package com.binge.server.model;

import javax.persistence.*;

/**
 * Created by zlb on 2016/3/10.
 */
@Entity
@Table(name = "brand_info", schema = "testweb", catalog = "")
public class BrandInfoEntity {
    private int id;
    private Integer brandInfoTypeId;
    private String description;
    private String imagePath;
    private BrandInfoTypesEntity typeByTypeId;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "BrandInfoTypeId", nullable = true)
    public Integer getBrandInfoTypeId() {
        return brandInfoTypeId;
    }

    public void setBrandInfoTypeId(Integer brandInfoTypeId) {
        this.brandInfoTypeId = brandInfoTypeId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "ImagePath", nullable = true, length = 255)
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrandInfoEntity that = (BrandInfoEntity) o;

        if (id != that.id) return false;
        if (brandInfoTypeId != null ? !brandInfoTypeId.equals(that.brandInfoTypeId) : that.brandInfoTypeId != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (imagePath != null ? !imagePath.equals(that.imagePath) : that.imagePath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (brandInfoTypeId != null ? brandInfoTypeId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "BrandInfoTypeId", referencedColumnName = "BrandInfoTypeId")
    public BrandInfoTypesEntity getTypeByTypeId() {
        return typeByTypeId;
    }

    public void setTypeByTypeId(BrandInfoTypesEntity typeByTypeId) {
        this.typeByTypeId = typeByTypeId;
    }
}
