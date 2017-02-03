package com.ensoa.order.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ensoa.order.entity.Pageable;
import com.ensoa.order.entity.ProductEntity;

@Repository("productRepository")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class ProductRepositoryHibernate implements ProductRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public ProductEntity findOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = :id";
		ProductEntity product = (ProductEntity)session.createSQLQuery(sql)
			 	.addEntity(ProductEntity.class)
				.setParameter("id", id)
				.uniqueResult();
		session.getTransaction().commit();		
		return product;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductEntity> findAll() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sql = "SELECT * FROM PRODUCT";
		List<ProductEntity> products = session.createSQLQuery(sql)
				.addEntity(ProductEntity.class)
				.list();
		session.getTransaction().commit();		
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
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(ProductEntity product) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sql = "INSERT INTO PRODUCT(NAME, PRICE, DESCRIPTION) VALUES (:name, :price, :description)";
		session.createSQLQuery(sql)
				.setParameter("name",product.getName())
				.setParameter("price",  product.getPrice())
				.setParameter("description",  product.getDescription())
				.executeUpdate();
		String mysql = "SELECT LAST_INSERT_ID()";
		BigInteger id = (BigInteger)session.createSQLQuery(mysql).uniqueResult();
		product.setId(id.longValue());
		session.getTransaction().commit();		
		System.out.println(product + "가 저장되었습니다.");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(ProductEntity product) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sql = "UPDATE PRODUCT SET NAME = :name, PRICE = :price, DESCRIPTION = :description WHERE PRODUCT_ID = :id";
		session.createSQLQuery(sql)
				.setParameter("name",product.getName())
				.setParameter("price",  product.getPrice())
				.setParameter("description",  product.getDescription())
				.setParameter("id", product.getId())
				.executeUpdate();
		session.getTransaction().commit();		
		System.out.println(product + "가 갱신되었습니다.");
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sql = "DELETE FROM PRODUCT WHERE PRODUCT_ID = :id";
		session.createSQLQuery(sql)
				.setParameter("id", id)
				.executeUpdate();		
		session.getTransaction().commit();	
	}

}
