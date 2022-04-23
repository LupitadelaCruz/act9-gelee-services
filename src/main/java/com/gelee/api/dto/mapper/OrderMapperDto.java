package com.gelee.api.dto.mapper;

import org.springframework.stereotype.Component;

import com.gelee.api.dto.request.OrderJellyRequestDto;
import com.gelee.api.dto.response.OrderJellyResponseDto;
import com.gelee.api.model.Orders;
import com.gelee.api.model.Products;

/**
 * Clase mapper que convierte dto a model y model a dto
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Component
public class OrderMapperDto {

	public OrderJellyResponseDto ordersToOrderJellyResponseDto(Orders orders) {
		return OrderJellyResponseDto.builder().idProducto(orders.getProducts().getId())
				.nombrePedido(orders.getNombrePedido()).telefono(orders.getTelefono())
				.totalPagar(orders.getTotalPagar()).calle(orders.getCalle()).cantidad(orders.getCantidad())
				.colonia(orders.getColonia()).numeroExt(orders.getNumeroExt()).numeroInt(orders.getNumeroInt()).build();
	}

	public Orders orderJellyResponseDtoToOrders(OrderJellyRequestDto orderJellyRequestDto) {
		return Orders.builder().calle(orderJellyRequestDto.getCalle()).cantidad(orderJellyRequestDto.getCantidad())
				.colonia(orderJellyRequestDto.getColonia()).nombrePedido(orderJellyRequestDto.getNombrePedido())
				.telefono(orderJellyRequestDto.getTelefono()).numeroExt(orderJellyRequestDto.getNumeroExt())
				.numeroInt(orderJellyRequestDto.getNumeroInt())
				.products(Products.builder().id(orderJellyRequestDto.getIdProducto()).build())
				.totalPagar(orderJellyRequestDto.getTotalPagar()).build();
	}

}
