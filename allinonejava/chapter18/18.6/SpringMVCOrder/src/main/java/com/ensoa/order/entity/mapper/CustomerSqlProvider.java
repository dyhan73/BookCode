package com.ensoa.order.entity.mapper;

import org.apache.ibatis.jdbc.SQL;

import com.ensoa.order.domain.CustomerSearch;
import com.ensoa.order.entity.CustomerEntity;


public class CustomerSqlProvider {
	public String find(final CustomerSearch customerSearch) {
		return new SQL() {{
	 	 	SELECT("* ");
	 	 	FROM("CUSTOMER");
	 	 	if(customerSearch.getName() != null) {
	 	 		WHERE("NAME LIKE #{name}");
	 	 	}
	 	 	if(customerSearch.getAddress() != null) {
	 	 		WHERE("ADDRESS LIKE #{address}");
	 	 	}
	 	 	if(customerSearch.getEmail() != null) {
	 	 		WHERE("EMAIL LIKE #{email}");
	 	 	}
		}}.toString();
	}
	public String insert(final CustomerEntity customer) {
		return new SQL() {{
	 	 	INSERT_INTO("CUSTOMER");
	 	 	if(customer.getName() != null) {
	 	 		VALUES("NAME", "#{name}");
	 	 	}
	 	 	if(customer.getAddress() != null) {
	 	 		VALUES("ADDRESS", "#{address}");
	 	 	}
	 	 	if(customer.getEmail() != null) {
	 	 		VALUES("EMAIL", "#{email}");
	 	 	}	 	
		}}.toString();
	}
	public String update(final CustomerEntity customer) {
		return new SQL() {{
	 	 	UPDATE("CUSTOMER");
	 	 	if(customer.getName() != null) {
	 	 		SET("NAME = #{name}");
	 	 	}
	 	 	if(customer.getAddress() != null) {
	 	 		SET("ADDRESS = #{address}");
	 	 	}
	 	 	if(customer.getEmail() != null) {
	 	 		SET("EMAIL = #{email}");
	 	 	}	 	
		}}.toString();
	}
	public String delete(long custId) {
		return new SQL() {{
	 	 	DELETE_FROM("CUSTOMER");
	 	 	WHERE("CUSTOMER_ID = #{custId}");
		}}.toString();
	}
}