package com.gelee.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.gelee.api.dto.request.JellyRequestDto;
import com.gelee.api.dto.response.JellyResponseDto;
import com.gelee.api.model.Products;

/**
 * Clase mapper que convierte dto a model y model a dto
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Component
public class ProductsMapperDto {

	public JellyResponseDto productsToJellyResponseDto(Products products) {

		return JellyResponseDto.builder().descripcion(products.getDescripcion()).id(products.getId())
				.ingredientes(products.getIngredientes()).nombre(products.getNombre()).precio(products.getPrecio())
				.rendimientoPersonas(products.getPorcion()).stock(products.getStock()).build();

	}

	public Products jellyResponseDtoToProducts(JellyRequestDto jellyRequestDto) {
		
		return Products.builder().descripcion(jellyRequestDto.getDescripcion())
				.ingredientes(jellyRequestDto.getIngredientes()).nombre(jellyRequestDto.getNombre())
				.precio(jellyRequestDto.getPrecio()).porcion(jellyRequestDto.getRendimientoPersonas())
				.stock(jellyRequestDto.getStock()).build();
	}

	public List<JellyResponseDto> productsToJellyResponseDto(List<Products> products) {

		return products.stream()
				.map(dto -> JellyResponseDto.builder().descripcion(dto.getDescripcion()).id(dto.getId())
						.ingredientes(dto.getIngredientes()).nombre(dto.getNombre()).precio(dto.getPrecio())
						.rendimientoPersonas(dto.getPorcion()).stock(dto.getStock()).build())
				.collect(Collectors.toList());
	}

}
