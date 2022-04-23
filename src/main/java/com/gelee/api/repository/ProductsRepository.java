package com.gelee.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gelee.api.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>{
	
	public Optional<Products> findById (Integer id);

}
