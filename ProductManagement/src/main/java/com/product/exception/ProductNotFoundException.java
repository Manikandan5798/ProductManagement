package com.product.exception;

public class ProductNotFoundException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	private String productName;
	private String productId;
	private Long object;
	
	
	public ProductNotFoundException(String productName, String productId, Long object) {
		super();
		this.productName = productName;
		this.productId = productId;
		this.object = object;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductId() {
		return productId;
	}
	public Long getObject() {
		return object;
	}

	
	
	

}
	

