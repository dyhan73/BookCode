package com.ensoa.order.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.entity.Pageable;

@Repository("customerRepository")
public class CustomerRepositoryJdbcDaoSupport extends NamedParameterJdbcDaoSupport 
	implements CustomerRepository {
	private static final String SQL_GETALL = "select * from customer";	
	private static final String SQL_GETBYID = "select * from customer where customer_id = :id";	
	private static final String SQL_GETBYNAME = "select * from customer where name = :name";	
	private static final String SQL_INSERT = "insert into customer (name, address, email) values (:name, :address, :email)";	
	private static final String SQL_UPDATE = "update customer set name = :name, address = :address, email = :email where customer_id = :id";
	private static final String SQL_DELETE = "delete from customer where customer_id = :id";
	@Autowired
	public CustomerRepositoryJdbcDaoSupport(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	@Override
	public CustomerEntity findOne(long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return getNamedParameterJdbcTemplate().queryForObject(SQL_GETBYID, params,
				new CustomerRowMapper());
	}

	@Override
	public CustomerEntity findOneByName(String name) {
		List<CustomerEntity> customers = findByName(name);
		if(customers != null && customers.size() != 0)
			return customers.get(0);
		return null;
	}

	@Override
	public List<CustomerEntity> findAll() {
		return getNamedParameterJdbcTemplate().query(SQL_GETALL, 
				new CustomerRowMapper());
	}

	@Override
	public List<CustomerEntity> findByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		return getNamedParameterJdbcTemplate().query(SQL_GETBYNAME, params,
				new CustomerRowMapper());
	}

	@Override
	public List<CustomerEntity> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CustomerEntity customer) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", customer.getName());
		params.put("address",  customer.getAddress());
		params.put("email",  customer.getEmail());
		getNamedParameterJdbcTemplate().update(SQL_INSERT, params);
	}

	@Override
	public void update(CustomerEntity customer) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", customer.getName());
		params.put("address",  customer.getAddress());
		params.put("email",  customer.getEmail());
		params.put("id",  customer.getId());
		getNamedParameterJdbcTemplate().update(SQL_UPDATE, params);
	}
	@Override
	public void delete(long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		getNamedParameterJdbcTemplate().update(SQL_DELETE, params);
	}

}
