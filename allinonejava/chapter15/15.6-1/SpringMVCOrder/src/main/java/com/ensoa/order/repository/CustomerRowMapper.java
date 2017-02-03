package com.ensoa.order.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ensoa.order.entity.CustomerEntity;

public class CustomerRowMapper implements RowMapper<CustomerEntity> {
	@Override
	public CustomerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerEntity customer = new CustomerEntity();
		customer.setId(rs.getInt("customer_id"));
		customer.setName(rs.getString("name"));
		customer.setAddress(rs.getString("address"));
		customer.setEmail(rs.getString("email"));
		return customer;
	}
}
