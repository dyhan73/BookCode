package com.ensoa.order.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensoa.order.entity.CustomerEntity;

public interface CustomerRepositoryDataJpa extends JpaRepository<CustomerEntity, Long> {
	List<CustomerEntity> findByName(String name);
}
