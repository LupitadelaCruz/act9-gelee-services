package com.gelee.api.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase dto  JellyRequestDto
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JellyRequestDto {
		
	private String nombre;
	
	private double precio;
	
	private int stock;
	
	private String descripcion;
	
	private String ingredientes;
	
	private int rendimientoPersonas;

}
