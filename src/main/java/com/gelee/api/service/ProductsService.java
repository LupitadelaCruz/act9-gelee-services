package com.gelee.api.service;

import java.util.List;

import com.gelee.api.dto.request.JellyRequestDto;
import com.gelee.api.dto.response.JellyResponseDto;

public interface ProductsService {
	
	  public List<JellyResponseDto> findAll();
	  
	  public JellyResponseDto findByProductId(Integer id);
	  
	  public JellyResponseDto addProduct(JellyRequestDto jelly);

}
