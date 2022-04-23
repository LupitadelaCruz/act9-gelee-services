package com.gelee.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gelee.api.model.Orders;

@Repository
public interface OrdersRepository  extends JpaRepository<Orders, Integer>{
	
	public Optional<Orders> findById (Integer id);

}
