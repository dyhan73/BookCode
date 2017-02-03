package com.ensoa.order.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ensoa.order.entity.CustomerEntity;
import com.ensoa.order.entity.Pageable;

// @Repository("customerRepository")
public class CustomerRepositoryJDBC implements CustomerRepository {
	@Autowired
	private DataSource dataSource;
	private Connection connection = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private static final String SQL_GETALL = "select * from customer";	
	private static final String SQL_GETBYID = "select * from customer where customer_id = ?";	
	private static final String SQL_GETBYNAME = "select * from customer where name = ?";	
	private static final String SQL_INSERT = "insert into customer (name, address, email) values (?, ?, ?)";	
	private static final String SQL_UPDATE = "update customer set name = ?, address = ?, email = ? where customer_id = ?";
	private static final String SQL_DELETE = "delete from customer where customer_id = ?";
	@SuppressWarnings("finally")
	@Override
	public CustomerEntity findOne(long id) {
		CustomerEntity customer = null;
		try {	
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(SQL_GETBYID);
			stmt.setLong(1,  id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				customer = new CustomerEntity();
				customer.setId(rs.getInt("customer_id"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
			}
		}
		catch(SQLException e ) {
			System.out.println(e);
		}
		finally {
			try {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(connection != null) {
					connection.close();
					connection = null;
				}
				return customer;
			}
			catch(SQLException e) {
				System.out.println(e);
				return null;
			}
		}
	}

	@Override
	public CustomerEntity findOneByName(String name) {
		List<CustomerEntity> customers = findByName(name);
		if(customers != null && customers.size() != 0)
			return customers.get(0);
		return null;
	}

	@SuppressWarnings("finally")
	@Override
	public List<CustomerEntity> findAll() {
		List<CustomerEntity> customers = new ArrayList<CustomerEntity>();
		try {	
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(SQL_GETALL);
			rs = stmt.executeQuery();
			CustomerEntity customer = null;
			while(rs.next()) {
				customer = new CustomerEntity();
				customer.setId(rs.getInt("customer_id"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
				customers.add(customer);
			}
		}
		catch(SQLException e ) {
			System.out.println(e);
		}
		finally {
			try {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(connection != null) {
					connection.close();
					connection = null;
				}
				return customers;
			}
			catch(SQLException e) {
				System.out.println(e);
				return null;
			}
		}
	}

	@SuppressWarnings("finally")
	@Override
	public List<CustomerEntity> findByName(String name) {
		List<CustomerEntity> customers = new ArrayList<CustomerEntity>();
		try {	
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(SQL_GETBYNAME);
			stmt.setString(1,  name);
			rs = stmt.executeQuery();
			CustomerEntity customer = null;
			while(rs.next()) {
				customer = new CustomerEntity();
				customer.setId(rs.getInt("customer_id"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
				customers.add(customer);
			}
		}
		catch(SQLException e ) {
			System.out.println(e);
		}
		finally {
			try {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(connection != null) {
					connection.close();
					connection = null;
				}
				return customers;
			}
			catch(SQLException e) {
				System.out.println(e);
				return null;
			}
		}
	}

	@Override
	public List<CustomerEntity> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CustomerEntity customer) {
		try {	
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(SQL_INSERT);
			stmt.setString(1, customer.getName());
			stmt.setString(2,  customer.getAddress());
			stmt.setString(3,  customer.getEmail());
			stmt.executeUpdate();
		}
		catch(SQLException e ) {
			System.out.println(e);
		}
		finally {
			try {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(connection != null) {
					connection.close();
					connection = null;
				}
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void update(CustomerEntity customer) {
		try {	
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(SQL_UPDATE);
			stmt.setString(1, customer.getName());
			stmt.setString(2,  customer.getAddress());
			stmt.setString(3,  customer.getEmail());
			stmt.setLong(4,  customer.getId());
			stmt.executeUpdate();
		}
		catch(SQLException e ) {
			System.out.println(e);
		}
		finally {
			try {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(connection != null) {
					connection.close();
					connection = null;
				}
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}
	}
	@Override
	public void delete(long id) {
		try {	
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(SQL_DELETE);
			stmt.setLong(1,  id);
			stmt.execute();
		}
		catch(SQLException e ) {
			System.out.println(e);
		}
		finally {
			try {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(connection != null) {
					connection.close();
					connection = null;
				}
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}
	}

}
