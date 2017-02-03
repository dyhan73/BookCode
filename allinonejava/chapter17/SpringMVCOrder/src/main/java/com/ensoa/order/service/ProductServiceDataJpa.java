package com.ensoa.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensoa.order.domain.Product;
import com.ensoa.order.entity.ProductEntity;
import com.ensoa.order.repository.ProductRepositoryDataJpa;

@Service("productService")
@Transactional
public class ProductServiceDataJpa implements ProductService {
	@Autowired
	private ProductRepositoryDataJpa repository;
	
	@Override
	public Product getProduct(long id) {
		ProductEntity product = repository.findOne(id);
		return product.buildDomain();
	}

	@Override
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findAll();
		for(ProductEntity entity : entities) {
			Product product = entity.buildDomain();
			products.add(product);
		}
		return products;
	}

	@Override
	public List<Product> getProductsByPage(int index, int size) {
		Pageable page = new PageRequest(index, size);
		List<Product> products = new ArrayList<Product>();
		Page<ProductEntity> entities = repository.findAll(page);
		for(ProductEntity entity : entities.getContent()) {
			Product product = entity.buildDomain();
			products.add(product);
		}
		return products;
	}

	@Override
	@Transactional(rollbackFor = NotFoundException.class)
	public void saveProduct(Product product) {
		ProductEntity entity = new ProductEntity();
		entity.buildEntity(product);
		repository.save(entity);
	}

	@Override
	@Transactional(rollbackFor = NotFoundException.class)
	public void updateProduct(Product product) {
		ProductEntity entity = repository.findOne(product.getId());
		entity.setName(product.getName());
		entity.setPrice(product.getPrice());
		entity.setDescription(product.getDescription());
	//	repository.save(entity);
	}

	@Override
	@Transactional(rollbackFor = NotFoundException.class)
	public void deleteProduct(long id) {
		repository.delete(id);
	}

}
