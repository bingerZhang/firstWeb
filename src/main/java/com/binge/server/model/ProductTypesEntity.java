package com.binge.server.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by zlb on 2016/3/10.
 */
@Entity
@Table(name = "product_types", schema = "testweb", catalog = "")
public class ProductTypesEntity {
    private int productTypeId;
    private String productType;
    private Collection<ProductsEntity> productsById;

    @Id
    @Column(name = "ProductTypeId", nullable = false)
    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    @Basic
    @Column(name = "ProductType", nullable = true, length = 255)
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductTypesEntity that = (ProductTypesEntity) o;

        if (productTypeId != that.productTypeId) return false;
        if (productType != null ? !productType.equals(that.productType) : that.productType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productTypeId;
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "TypeByTypeId")
    public Collection<ProductsEntity> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<ProductsEntity> productsById) {
        this.productsById = productsById;
    }
}
