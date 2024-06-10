package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;
import com.product.services.ProductServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("product-service")
public class ProductController {

	@Autowired
	public ProductServices productService;
	
	// create a product
	@PostMapping("/")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		
		try {
			product = productService.saveProduct(product);
			
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//get a product
	
	@GetMapping("{id}")
	@ResponseStatus(code = HttpStatus.OK)
    public Product fetchProductById(@PathVariable("id") Long id){
		
        return productService.fetchProductById(id);
    }
	
	//get all product
	
	@GetMapping("/")
	@ResponseStatus(code = HttpStatus.OK)
    public List<Product> fetchAllProduct(){
		
        return productService.fetchAllProduct();
    }
	
	//delete a product
	
	@DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Long id) {
		
	
		try {
			productService.deleteProductById(id);
			return new ResponseEntity<String>("Product details deleted Successfully!", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Product not found!", HttpStatus.NOT_FOUND);
		}
    }
	
	//update a product
	
	@PutMapping("{id}")
	public ResponseEntity<Product> updateProductDetails(@PathVariable("id") Long id
												  ,@RequestBody Product product){
		
	
		Product _product=productService.updateProductDetails(product, id);
		return new ResponseEntity<Product>(_product, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/")
	  public ResponseEntity<String> deleteAllProducts() {
	    try {
	      productService.deleteAllProduct();
	      return new ResponseEntity<String>("All Product deleted successfully",HttpStatus.ACCEPTED);
	    } catch (Exception e) {
	      return new ResponseEntity<String>("Product not found!", HttpStatus.NOT_FOUND);
	    }

	  }
	
	
}
