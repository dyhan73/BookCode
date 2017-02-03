package com.ensoa.order.entity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.ensoa.order.entity.ProductEntity;

public interface ProductMapperWithAnnotation {
	@Select("SELECT PRODUCT_ID AS ID, NAME, PRICE, DESCRIPTION FROM PRODUCT")
	List<ProductEntity> findAll();	
	List<ProductEntity> findAll(RowBounds rowBounds);	
	@Select("SELECT PRODUCT_ID AS ID, NAME, PRICE, DESCRIPTION FROM PRODUCT 	WHERE PRODUCT_ID=#{id}")
	@Results( {
		@Result(id=true, property="id", column="product_id"),
		@Result(property="name", column="name"),
		@Result(property="price", column="price"),
		@Result(property="description", column="description")
	})
  	ProductEntity findById(long id);	
  	@Insert("INSERT INTO PRODUCT(NAME, PRICE, DESCRIPTION) VALUES (#{name}, #{price}, #{description})")
  	@Options(useGeneratedKeys=true, keyProperty="prodId")
  	void insert(ProductEntity Product);
  	@Update("UPDATE PRODUCT SET NAME = #{name}, PRICE = #{price}, DESCRIPTION = #{description} WHERE PRODUCT_ID = #{id}")
  	void update(ProductEntity Product);
  	@Delete("DELETE FROM PRODUCT WHERE PRODUCT_ID = #{id}")
  	void delete(long id);
}
