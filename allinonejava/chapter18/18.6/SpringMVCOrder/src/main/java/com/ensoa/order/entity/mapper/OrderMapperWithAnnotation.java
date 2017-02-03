package com.ensoa.order.entity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ensoa.order.entity.OrderEntity;


public interface OrderMapperWithAnnotation {
	@Select("SELECT * FROM ORDERS")
	@Results( {
		@Result(id=true, property="orderId", column="order_id" ),
		@Result(property="orderDate", column="order_date"),
		@Result(property="customer", column="customer_id", 
						one=@One(select="com.ensoa.order.entity.mapper.CustomerMapper.findById")),
		@Result(property="items", column="order_id",
						many=@Many(select="com.ensoa.order.entity.mapper.OrderItemMapper.findByOrderId"))
	})
	List<OrderEntity> findAll();
	@Select("SELECT * FROM ORDERS WHERE ORDERS.ORDER_ID = #{orderId}")
	@Results( {
		@Result(id=true, property="id", column="order_id" ),
		@Result(property="orderDate", column="order_date"),
		@Result(property="customer", column="customer_id", 
						one=@One(select="com.ensoa.order.entity.mapper.CustomerMapper.findById")),
		@Result(property="items", column="order_id",
						many=@Many(select="com.ensoa.order.entity.mapper.OrderItemMapper..findByOrderId"))
	})
	OrderEntity findById(long id);
    @Insert("INSERT INTO ORDERS(CUSTOMER_ID, ORDER_DATE) VALUES (#{customer.id}, #{orderDate})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(OrderEntity order);
    @Update("UPDATE ORDERS SET ORDER_DATE = #{orderDate} WHERE ORDER_ID = #{id}")
    void update(OrderEntity order);
    @Delete("DELETE FROM ORDERS WHERE ORDER_ID = #{id}")
    void delete(long id);
}
