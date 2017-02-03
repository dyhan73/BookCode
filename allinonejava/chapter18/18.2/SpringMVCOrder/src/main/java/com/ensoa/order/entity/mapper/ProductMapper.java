package com.ensoa.order.entity.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.ensoa.order.entity.ProductEntity;

public interface ProductMapper {
	List<ProductEntity> findAll();
	List<ProductEntity> findAll(RowBounds rowBounds);
    ProductEntity findById(long id);
    void insert(ProductEntity Product);
    void update(ProductEntity Product);
    void delete(long id);
}
