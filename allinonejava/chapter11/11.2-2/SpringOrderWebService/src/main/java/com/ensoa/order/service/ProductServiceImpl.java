package com.ensoa.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensoa.order.domain.Product;
import com.ensoa.order.entity.PageRequest;
import com.ensoa.order.entity.Pageable;
import com.ensoa.order.entity.ProductEntity;
import com.ensoa.order.repository.OrderRepository;
import com.ensoa.order.repository.ProductRepository;

//@Service("productService")
public class ProductServiceImpl implements ProductService {
	// @Autowired
	private ProductRepository productRepository;
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	@Override
	public Product getProduct(long id) {
		ProductEntity product = productRepository.findOne(id);
		return product.buildDomain();
	}

	@Override
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		List<ProductEntity> entities = productRepository.findAll();
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
		List<ProductEntity> entities = productRepository.findAll(page);
		for(ProductEntity entity : entities) {
			Product product = entity.buildDomain();
			products.add(product);
		}
		return products;
	}

	@Override
	public void saveProduct(Product product) {
		ProductEntity entity = new ProductEntity();
		entity.buildEntity(product);
		productRepository.save(entity);
	}

	@Override
	public void updateProduct(Product product) {
		ProductEntity entity = new ProductEntity();
		entity.buildEntity(product);
		productRepository.save(entity);
	}

	@Override
	public void deleteProduct(long id) {
		productRepository.delete(id);
	}

}
