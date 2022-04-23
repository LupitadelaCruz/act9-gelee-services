package com.gelee.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.gelee.api.dto.request.JellyRequestDto;
import com.gelee.api.dto.response.JellyResponseDto;
import com.gelee.api.service.ProductsService;
import com.gelee.api.util.Constants;

/**
 * Clase Controladora que expone apis para dar de alta y consultar productos, gelatinas en venta.
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.BASE_PATH)
public class ProductsController {
	
	private ProductsService productsService;
	
	@Autowired
	public ProductsController(ProductsService productsService) {
		this.productsService = productsService;
	}
	
	@GetMapping(path = Constants.PRODUCTS_URI,  produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','OPERATION')")
	public List<JellyResponseDto> getAllProducts() {

		return productsService.findAll();
	}

	@GetMapping(path = Constants.GET_PRODUCT_ID_URI,  produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','OPERATION')")
	public JellyResponseDto getCustomer(@PathVariable Integer id) {

		return productsService.findByProductId(id);
	}

	@PostMapping(path = Constants.PRODUCTS_URI, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public JellyResponseDto addCustomer(@RequestBody JellyRequestDto jelly) {

		return productsService.addProduct(jelly);
	}

}
