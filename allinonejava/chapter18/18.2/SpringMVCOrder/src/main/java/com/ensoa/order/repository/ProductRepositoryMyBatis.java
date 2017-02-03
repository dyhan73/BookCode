package com.ensoa.order.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.ensoa.order.entity.Pageable;
import com.ensoa.order.entity.ProductEntity;
import com.ensoa.order.entity.mapper.ProductMapper;

@Repository("productRepository")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class ProductRepositoryMyBatis implements ProductRepository {
	@Autowired
	private ProductMapper mapper;
	
	@Override
	public ProductEntity findOne(long id) {
		ProductEntity product = mapper.findById(id);
		return product;
	}

	@Override
	public List<ProductEntity> findAll() {
		List<ProductEntity> products = mapper.findAll();
		return products;
	}

	@Override
	public List<ProductEntity> findAll(Pageable page) {
		RowBounds rowBounds = new RowBounds(page.getIndex(), page.getSize());
		List<ProductEntity> products = mapper.findAll(rowBounds);
		return products;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(ProductEntity product) {
		mapper.insert(product);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(ProductEntity product) {
		mapper.update(product);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(long id) {
		mapper.delete(id);
	}

}
