package com.ensoa.order.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ensoa.order.domain.Customer;
import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.entity.Pageable;
import com.ensoa.order.entity.ProductEntity;

// @Repository("customerRepository")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class CustomerRepositoryJPACriteria implements CustomerRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public CustomerEntity findOne(long id) {
		return  entityManager.find(CustomerEntity.class, id);
	}

	@Override
	public CustomerEntity findOneByName(String name) {
		List<CustomerEntity> customers = findByName(name);
		if(customers.size() > 0)
			return customers.get(0);
		return null;
	}
	
	@Override
	public List<CustomerEntity> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CustomerEntity> criteria = builder.createQuery(CustomerEntity.class);
		Root<CustomerEntity> root = criteria.from(CustomerEntity.class);
		criteria.select(root);
		return entityManager.createQuery(criteria)
				.getResultList();
	}
	
	@Override
	public List<CustomerEntity> findByName(String name) {
		if(name == null)
			return null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CustomerEntity> criteria = builder.createQuery(CustomerEntity.class);
		Root<CustomerEntity> root = criteria.from(CustomerEntity.class);
		criteria.select(root);
		criteria.where(builder.equal(root.get("name"), name));
		return entityManager.createQuery(criteria)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerEntity> findAll(Pageable page) {
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(CustomerEntity customer) {
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
		System.out.println(customer + "가 저장되었습니다.");
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(CustomerEntity customer) {
		entityManager.getTransaction().begin();
		CustomerEntity entity = findOne(customer.getId());
		entity.setName(customer.getName());
		entity.setAddress(customer.getAddress());
		entity.setEmail(customer.getEmail());
		entityManager.getTransaction().commit();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(long id) {
		entityManager.getTransaction().begin();
		CustomerEntity customer = findOne(id);
		entityManager.remove(customer);
		entityManager.getTransaction().commit();
	}

}
