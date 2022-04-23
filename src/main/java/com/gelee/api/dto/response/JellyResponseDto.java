package com.gelee.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase dto  JellyResponseDto
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JellyResponseDto {
	
	private Integer id;
	
	private String nombre;
	
	private double precio;
	
	private Integer stock;
	
	private String descripcion;
	
	private String ingredientes;
	
	private Integer rendimientoPersonas;

}
