package com.ensoa.order.entity.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.ensoa.order.domain.CustomerSearch;
import com.ensoa.order.entity.CustomerEntity;

public interface CustomerMapper {
	List<CustomerEntity> findAll();
	List<CustomerEntity> findAll(RowBounds rowBounds);
    CustomerEntity findById(long id);
    List<CustomerEntity> findByName(String name);
    void insert(CustomerEntity customer);
    void update(CustomerEntity customer);
    void delete(long id);
    List<CustomerEntity> find(CustomerSearch customerSearch);
    List<CustomerEntity> findBySp(String name);
}
