package com.gelee.api.service.impl;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gelee.api.dto.mapper.OrderMapperDto;
import com.gelee.api.dto.request.OrderJellyRequestDto;
import com.gelee.api.dto.response.OrderJellyResponseDto;
import com.gelee.api.exceptions.BadRequestException;
import com.gelee.api.exceptions.NoDataFoundException;
import com.gelee.api.repository.OrdersRepository;
import com.gelee.api.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase  service que maneja funcionalidad alta y consulta de ordenes de gelatinas
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

	private OrdersRepository ordersRepository;

	private OrderMapperDto orderMapperDto;

	@Autowired
	public OrderServiceImpl(OrdersRepository ordersRepository, OrderMapperDto orderMapperDto) {
		this.orderMapperDto = orderMapperDto;
		this.ordersRepository = ordersRepository;

	}

	@Override
	public OrderJellyResponseDto findOrderById(Integer id) {
		log.debug("findOrderById {} ", id);
		if (id < 0 || Objects.isNull(id)) {
			log.error("Error: Invalid parameter {} ", id);
			throw new BadRequestException("Invalid id parameter", Arrays.asList("id"));
		}

		return orderMapperDto.ordersToOrderJellyResponseDto(
				ordersRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Order not found")));
	}

	@Override
	public OrderJellyResponseDto addOrder(OrderJellyRequestDto order) {
		if (Objects.isNull(order)) {
			log.error("Error: Invalid parameters {} ", order);
			throw new BadRequestException("Invalid id parameters", Arrays.asList("order"));
		}

		return orderMapperDto.ordersToOrderJellyResponseDto(
				ordersRepository.save(orderMapperDto.orderJellyResponseDtoToOrders(order)));
	}

}
