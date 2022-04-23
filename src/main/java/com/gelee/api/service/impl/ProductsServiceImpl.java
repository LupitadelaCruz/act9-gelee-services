package com.gelee.api.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gelee.api.dto.mapper.ProductsMapperDto;
import com.gelee.api.dto.request.JellyRequestDto;
import com.gelee.api.dto.response.JellyResponseDto;
import com.gelee.api.exceptions.BadRequestException;
import com.gelee.api.exceptions.NoDataFoundException;
import com.gelee.api.repository.ProductsRepository;
import com.gelee.api.service.ProductsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase  service que maneja funcionalidad de alta y consulta de geletinas en catalogo
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductsServiceImpl implements ProductsService {

	private ProductsMapperDto productsMapperDto;

	private ProductsRepository productsRepository;

	@Autowired
	public ProductsServiceImpl(ProductsMapperDto productsMapperDto, ProductsRepository productsRepository) {
		this.productsMapperDto = productsMapperDto;
		this.productsRepository = productsRepository;
	}

	@Override
	public List<JellyResponseDto> findAll() {
		return productsMapperDto.productsToJellyResponseDto(Optional.of(productsRepository.findAll())
				.orElseThrow(() -> new NoDataFoundException("Product not found")));
	}

	@Override
	public JellyResponseDto findByProductId(Integer id) {
		return productsMapperDto.productsToJellyResponseDto(
				productsRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Product not found")));
	}

	@Override
	public JellyResponseDto addProduct(JellyRequestDto jellyRequestDto) {

		if (Objects.isNull(jellyRequestDto)) {
			log.error("Error: Invalid products parameters {} ", jellyRequestDto);
			throw new BadRequestException("Invalid products parameters", Arrays.asList("products"));
		}

		return productsMapperDto.productsToJellyResponseDto(
				productsRepository.save(productsMapperDto.jellyResponseDtoToProducts(jellyRequestDto)));
	}

}
