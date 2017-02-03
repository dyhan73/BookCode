package com.ensoa.order.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ensoa.order.entity.Pageable;
import com.ensoa.order.entity.ProductEntity;

// @Repository("productRepository")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class ProductRepositoryCriteria implements ProductRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public ProductEntity findOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		ProductEntity product = (ProductEntity)session.createCriteria(ProductEntity.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		session.getTransaction().commit();		
		return product;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductEntity> findAll() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<ProductEntity> products = session.createCriteria(ProductEntity.class)
				.list();
		session.getTransaction().commit();		
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductEntity> findAll(Pageable page) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<ProductEntity> products = session.createCriteria(ProductEntity.class)
				.setFirstResult(page.getIndex())		// offset
				.setMaxResults(page.getSize())		// limit
				.list();
		session.getTransaction().commit();		
		return products;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(ProductEntity product) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(product);
		session.getTransaction().commit();		
		System.out.println(product + "가 저장되었습니다.");
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(ProductEntity product) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(product);
		session.getTransaction().commit();	
		System.out.println(product + "가 갱신되었습니다.");
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(findOne(id));
		session.getTransaction().commit();	
	}

}
