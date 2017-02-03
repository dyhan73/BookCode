package com.ensoa.order.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.entity.OrderEntity;
import com.ensoa.order.entity.OrderItemEntity;
import com.ensoa.order.entity.Pageable;

@Repository("orderRepository")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class OrderRepositoryCriteria implements OrderRepository {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private CustomerRepository customerRepository;
	private ProductRepository productRepository;

	@Override
	public OrderEntity findOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		OrderEntity order = (OrderEntity)session.createCriteria(OrderEntity.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		session.getTransaction().commit();		
		return order;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderEntity> findAll() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<OrderEntity> orders = session.createCriteria(OrderEntity.class)
				.list();
		session.getTransaction().commit();		
		return orders;
	}

	@Override
	public List<OrderEntity> findAll(Pageable page) {
		List<OrderEntity> orders = new ArrayList<OrderEntity>();
		return orders;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(OrderEntity order) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();	
		CustomerEntity customer = (CustomerEntity)session.createCriteria(CustomerEntity.class)
				.add(Restrictions.eq("id", order.getCustomer().getId()))
				.uniqueResult();
		if(customer != null) {
			order.setCustomer(customer);
		}
		session.save(order);
		session.getTransaction().commit();
		System.out.println(order + "가 저장되었습니다.");
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(OrderEntity order) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();	
		session.update(order);
		session.getTransaction().commit();
		System.out.println(order + "가 갱신되었습니다.");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();	
		OrderEntity order = findOne(id);
		session.delete(order);
		session.getTransaction().commit();
		System.out.println(order + "가 삭되었습니다.");
	}

}
