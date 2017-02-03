package com.ensoa.order.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.entity.Pageable;
import com.ensoa.order.entity.ProductEntity;

@Repository("customerRepository")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class CustomerRepositoryHQL implements CustomerRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CustomerEntity findOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from CustomerEntity where id = :id";
		CustomerEntity customer = (CustomerEntity)session.createQuery(hql)
				.setParameter("id", id)
				.uniqueResult();
		session.getTransaction().commit();		
		return customer;
	}

	@Override
	public CustomerEntity findOneByName(String name) {
		List<CustomerEntity> customers = findByName(name);
		if(customers.size() > 0)
			return customers.get(0);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerEntity> findAll() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from CustomerEntity";
		List<CustomerEntity> customers = session.createQuery(hql)
				.list();
		session.getTransaction().commit();		
		return customers;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerEntity> findByName(String name) {
		if(name == null)
			return null;
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from CustomerEntity where name = :name";
		List<CustomerEntity> customers = session.createQuery(hql)
				.setParameter("name",  name)
				.list();
		session.getTransaction().commit();		
		return customers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerEntity> findAll(Pageable page) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from CustomerEntity";
		List<CustomerEntity> customers = session.createQuery(hql)
				.setFirstResult(page.getIndex())	// offset
				.setMaxResults(page.getSize())	// limit
				.list();
		session.getTransaction().commit();		
		return customers;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(CustomerEntity customer) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();				
		System.out.println(customer + "가 저장되었습니다.");
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(CustomerEntity customer) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "update CustomerEntity set name = :name, address = :address, email = :email where id = :id";
		session.createQuery(hql)
				.setParameter("name",  customer.getName())
				.setParameter("address",  customer.getAddress())
				.setParameter("email", customer.getEmail())
				.setParameter("id",  customer.getId())
				.executeUpdate();
		session.getTransaction().commit();		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "delete CustomerEntity where id = :id";
		session.createQuery(hql)
				.setParameter("id",  id)
				.executeUpdate();
		session.getTransaction().commit();		
	}

}
