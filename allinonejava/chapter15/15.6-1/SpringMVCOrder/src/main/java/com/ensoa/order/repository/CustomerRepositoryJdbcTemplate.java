package com.ensoa.order.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.entity.Pageable;

// @Repository("customerRepository")
public class CustomerRepositoryJdbcTemplate implements CustomerRepository {
	private static final String SQL_GETALL = "select * from customer";	
	private static final String SQL_GETBYID = "select * from customer where customer_id = ?";	
	private static final String SQL_GETBYNAME = "select * from customer where name = ?";	
	private static final String SQL_INSERT = "insert into customer (name, address, email) values (?, ?, ?)";	
	private static final String SQL_UPDATE = "update customer set name = ?, address = ?, email = ? where customer_id = ?";
	private static final String SQL_DELETE = "delete from customer where customer_id = ?";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public CustomerEntity findOne(long id) {
		Object[] params = new Object[] { id };
		return jdbcTemplate.queryForObject(SQL_GETBYID, params,
				new RowMapper<CustomerEntity>() {
					@Override
					public CustomerEntity mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						CustomerEntity customer = new CustomerEntity();
						customer.setId(rs.getInt("customer_id"));
						customer.setName(rs.getString("name"));
						customer.setAddress(rs.getString("address"));
						customer.setEmail(rs.getString("email"));
						return customer;
					}
			
		});
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
		return jdbcTemplate.query(SQL_GETALL, 
				new RowMapper<CustomerEntity>() {
					@Override
					public CustomerEntity mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						CustomerEntity customer = new CustomerEntity();
						customer.setId(rs.getInt("customer_id"));
						customer.setName(rs.getString("name"));
						customer.setAddress(rs.getString("address"));
						customer.setEmail(rs.getString("email"));
						return customer;
					}
			
		});
	}

	@Override
	public List<CustomerEntity> findByName(String name) {
		Object[] params = new Object[] { name };
		return jdbcTemplate.query(SQL_GETBYNAME, params,
				new RowMapper<CustomerEntity>() {
					@Override
					public CustomerEntity mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						CustomerEntity customer = new CustomerEntity();
						customer.setId(rs.getInt("customer_id"));
						customer.setName(rs.getString("name"));
						customer.setAddress(rs.getString("address"));
						customer.setEmail(rs.getString("email"));
						return customer;
					}
			
		});
	}

	@Override
	public List<CustomerEntity> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CustomerEntity customer) {
		jdbcTemplate.update(SQL_INSERT, 
			customer.getName(),
			customer.getAddress(),
			customer.getEmail());
	}

	@Override
	public void update(CustomerEntity customer) {
		jdbcTemplate.update(SQL_UPDATE, 
				customer.getName(),
				customer.getAddress(),
				customer.getEmail(),
				customer.getId());
	}
	@Override
	public void delete(long id) {
		CustomerEntity customer = findOne(id);
		if(customer.getName().equals("전병선"))
			throw new RuntimeException();
		jdbcTemplate.update(SQL_DELETE, id);
	}

}
