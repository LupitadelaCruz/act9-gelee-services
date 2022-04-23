package com.gelee.api.service;

import com.gelee.api.dto.request.OrderJellyRequestDto;
import com.gelee.api.dto.response.OrderJellyResponseDto;

public interface OrderService {
	
	 public OrderJellyResponseDto findOrderById(Integer id);
	  
	 public OrderJellyResponseDto addOrder(OrderJellyRequestDto order);

}
