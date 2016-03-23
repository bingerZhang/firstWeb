package com.binge.server.model;

import javax.persistence.*;

/**
 * Created by zlb on 2016/3/10.
 */
@Entity
@Table(name = "brand_info_types", schema = "testweb", catalog = "")
public class BrandInfoTypesEntity {
    private int brandInfoTypeId;
    private String brandInfoType;

    @Id
    @Column(name = "BrandInfoTypeId", nullable = false)
    public int getBrandInfoTypeId() {
        return brandInfoTypeId;
    }

    public void setBrandInfoTypeId(int brandInfoTypeId) {
        this.brandInfoTypeId = brandInfoTypeId;
    }

    @Basic
    @Column(name = "BrandInfoType", nullable = true, length = 255)
    public String getBrandInfoType() {
        return brandInfoType;
    }

    public void setBrandInfoType(String brandInfoType) {
        this.brandInfoType = brandInfoType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrandInfoTypesEntity that = (BrandInfoTypesEntity) o;

        if (brandInfoTypeId != that.brandInfoTypeId) return false;
        if (brandInfoType != null ? !brandInfoType.equals(that.brandInfoType) : that.brandInfoType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = brandInfoTypeId;
        result = 31 * result + (brandInfoType != null ? brandInfoType.hashCode() : 0);
        return result;
    }
}
