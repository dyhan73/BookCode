package com.ensoa.order.entity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.session.RowBounds;

import com.ensoa.order.domain.CustomerSearch;
import com.ensoa.order.entity.CustomerEntity;

public interface CustomerMapperWithAnnotation {
	@Select("SELECT * FROM CUSTOMER")
	@ResultMap("com.ensoa.order.entity.mapper.CustomerMapper.CustomerResult")
	List<CustomerEntity> findAll();
	List<CustomerEntity> findAll(RowBounds rowBounds);
	@Select("SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=#{custId}")
	@ResultMap("com.ensoa.order.entity.mapper.CustomerMapper.CustomerResult")	
    CustomerEntity findById(long id);
	@Select("SELECT * FROM CUSTOMER WHERE NAME=#{name}")
	@ResultMap("com.ensoa.order.entity.mapper.CustomerMapper.CustomerResult")	
    List<CustomerEntity> findByName(String name);
    @InsertProvider(type=CustomerSqlProvider.class, method="insert")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(CustomerEntity customer);
    @UpdateProvider(type=CustomerSqlProvider.class, method="update")
    void update(CustomerEntity customer);
    @DeleteProvider(type=CustomerSqlProvider.class, method="delete")
    void delete(long id);
    @SelectProvider(type=CustomerSqlProvider.class, method="find")
	@ResultMap("com.ensoa.order.entity.mapper.CustomerMapper.CustomerResult")
    List<CustomerEntity> find(CustomerSearch customerSearch);
    @Select(value= "{CALL GET_CUSTOMERS(#{name, mode=IN, jdbcType=VARCHAR})}")
	@ResultMap("com.ensoa.order.entity.mapper.CustomerMapper.CustomerResult")
    @Options(statementType = StatementType.CALLABLE)
    List<CustomerEntity> findBySp(String name);
}
