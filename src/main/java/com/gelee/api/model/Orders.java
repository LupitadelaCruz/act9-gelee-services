package com.gelee.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase  Entity Orders
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Entity(name="pedidos")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Products products;
	
	private String nombrePedido;
	
	private Integer cantidad;
	
	private Double totalPagar;
	
	private String calle;
	
	private String numeroExt;
	
	private String numeroInt;
	
	private String colonia;
	
	private String telefono;

}
