package com.gelee.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gelee.api.dto.request.OrderJellyRequestDto;
import com.gelee.api.dto.response.OrderJellyResponseDto;
import com.gelee.api.service.OrderService;
import com.gelee.api.util.Constants;


import lombok.RequiredArgsConstructor;

/**
 * Clase Controlladora que expone las apis para crear y consultar ordenes de gelatinas.
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.BASE_PATH)
public class OrdersController {
	
	private OrderService orderService;
	
	@Autowired
	public OrdersController(OrderService orderService) {
		this.orderService =  orderService;		
	}
	
	@PostMapping(path = Constants.ORDERS_URI_ADD, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','OPERATION')")
	public OrderJellyResponseDto addCustomer(@RequestBody OrderJellyRequestDto order) {

		return orderService.addOrder(order);
	}
		
	
	@GetMapping(path = Constants.ORDERS_URI_ID_URI,  produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','OPERATION')")
	public OrderJellyResponseDto getOrderById(@PathVariable Integer id) {
		return orderService.findOrderById(id);
	}

}
