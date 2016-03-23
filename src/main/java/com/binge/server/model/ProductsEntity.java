package com.binge.server.model;

import javax.persistence.*;
import java.security.Timestamp;

/**
 * Created by zlb on 2016/3/10.
 */
@Entity
@Table(name = "products", schema = "testweb", catalog = "")
public class ProductsEntity {
    private int id;
    private String productName;
    private Integer productTypeId;
    private String productDesc;
    private String productPicPath;
    private Timestamp createTime;
    private ProductTypesEntity TypeByTypeId;

    public void setCreateTime(java.security.Timestamp createTime) {
        this.createTime = createTime;
    }

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ProductName", nullable = true, length = 128)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "ProductTypeId", nullable = true)
    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    @Basic
    @Column(name = "ProductDesc", nullable = true, length = 1024)
    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    @Basic
    @Column(name = "ProductPicPath", nullable = true, length = 1024)
    public String getProductPicPath() {
        return productPicPath;
    }

    public void setProductPicPath(String productPicPath) {
        this.productPicPath = productPicPath;
    }

    @Basic
    @Column(name = "CreateTime", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsEntity that = (ProductsEntity) o;

        if (id != that.id) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productTypeId != null ? !productTypeId.equals(that.productTypeId) : that.productTypeId != null)
            return false;
        if (productDesc != null ? !productDesc.equals(that.productDesc) : that.productDesc != null) return false;
        if (productPicPath != null ? !productPicPath.equals(that.productPicPath) : that.productPicPath != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productTypeId != null ? productTypeId.hashCode() : 0);
        result = 31 * result + (productDesc != null ? productDesc.hashCode() : 0);
        result = 31 * result + (productPicPath != null ? productPicPath.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Id", referencedColumnName = "ProductTypeId", nullable = false)
    public ProductTypesEntity getTypeByTypeId() {
        return TypeByTypeId;
    }

    public void setTypeByTypeId(ProductTypesEntity typeByTypeId) {
        TypeByTypeId = typeByTypeId;
    }
}
