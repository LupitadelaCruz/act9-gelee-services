package com.gelee.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase  Entity Products
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Entity(name="prodcutos")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	private double precio;
	
	private String nombre;
	
	private int stock;
	
	private String descripcion;
	
	private String ingredientes;
	
	private int porcion;	
}
