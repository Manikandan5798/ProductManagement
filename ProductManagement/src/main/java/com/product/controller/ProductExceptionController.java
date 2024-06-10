package com.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.product.exception.ProductNotFoundException;

public class ProductExceptionController {

	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<Object> exception(ProductNotFoundException exception)
	{
		return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	}

}
