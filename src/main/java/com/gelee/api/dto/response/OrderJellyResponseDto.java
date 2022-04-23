package com.gelee.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase dto  OrderJellyResponseDto
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderJellyResponseDto {
	
	private int idProducto;
	
	private String nombrePedido;
	
	private int cantidad;
	
	private Double totalPagar;
	
	private String calle;
	
	private String numeroExt;
	
	private String numeroInt;
	
	private String colonia;
	
	private String telefono;

}
