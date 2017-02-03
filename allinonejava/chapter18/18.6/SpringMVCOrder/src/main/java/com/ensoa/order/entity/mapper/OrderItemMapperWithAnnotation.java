package com.ensoa.order.entity.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Update;

import com.ensoa.order.entity.OrderItemEntity;

public interface OrderItemMapperWithAnnotation {
	@Select("SELECT * FROM ORDER_ITEM WHERE ORDER_ID = #{id}")
	@Results( {
		@Result(id=true, property="id", column="order_item_id" ),
		@Result(property="amount", column="amount"),
		@Result(property="product", column="product_id", 
		 one=@One(select="com.ensoa.order.entity.mapper.ProductMapper.findById"))
	})
	List<OrderItemEntity> findByOrderId(long orderId);
	@Select("SELECT * FROM ORDER_ITEM WHERE ORDER_ITEM_ID=#{itemId}")
	@Results( {
		@Result(id=true, property="id", column="order_item_id" ),
		@Result(property="amount", column="amount"),
		@Result(property="product", column="product_id", 
		 one=@One(select="com.ensoa.order.entity.mapper.ProductMapper.findById"))
	})
	OrderItemEntity findById(long id);
    @Insert("INSERT INTO ORDER_ITEM(AMOUNT, PRODUCT_ID, ORDER_ID) VALUES (#{amount}, #{product.id}, #{orderId})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(OrderItemEntity orderItem);
    @Update("UPDATE ORDER_ITEM SET AMOUNT = #{amount} WHERE ORDER_ITEM_ID = #{od}")
    void update(OrderItemEntity orderItem);
    @Delete("DELETE FROM ORDER_ITEM WHERE ORDER_ITEM_ID = #{id}")
    void delete(long id);
}
