package com.gelee.api.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase dto  OrderJellyRequestDto
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderJellyRequestDto {
	
	private int idProducto;
	
	private String nombrePedido;
	
	private int cantidad;
	
	private double totalPagar;
	
	private String calle;
	
	private String numeroExt;
	
	private String numeroInt;
	
	private String colonia;
	
	private String telefono;

}
