package com.ensoa.order.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ensoa.order.entity.PageRequest;
import com.ensoa.order.entity.Pageable;
import com.ensoa.order.entity.ProductEntity;

// @Repository("productRepository")
public class ProductRepositoryImpl implements ProductRepository {

	@Override
	public ProductEntity findOne(long id) {
		ProductEntity product = new ProductEntity();
		product.setId(id);
		product.setName("제품" + id);
		product.setPrice((int)(10000 * id));
		product.setDescription("제품 설명" + id);
		return product;
	}

	@Override
	public List<ProductEntity> findAll() {
		List<ProductEntity> products = new ArrayList<ProductEntity>();
		for(int i =0; i < 10; ++i) {
			ProductEntity product = new ProductEntity();
			product.setId(i);
			product.setName("제품" + i);
			product.setPrice((i+1) * 10000);
			product.setDescription("제품 설명" + i);
			products.add(product);
		}
		return products;
	}

	@Override
	public List<ProductEntity> findAll(Pageable page) {
		List<ProductEntity> products = new ArrayList<ProductEntity>();
		int index = page.getIndex();
		int size = page.getSize();
		for(int i = index; i < index+size; ++i) {
			ProductEntity product = new ProductEntity();
			product.setId(i);
			product.setName("제품" + i);
			product.setPrice((i+1) * 10000);
			product.setDescription("제품 설명 " + i);
			products.add(product);
		}
		return products;
	}

	@Override
	public void save(ProductEntity product) {
		System.out.println(product + "가 저장되었습니다.");
	}

	@Override
	public void delete(long id) {
		ProductEntity product = findOne(id);
		System.out.println(product + "가 삭제되었습니다.");
	}

}
