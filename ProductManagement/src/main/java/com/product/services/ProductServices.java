package com.product.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.exception.ProductNotFoundException;
import com.product.model.Product;
import com.product.repository.ProductRepo;


@Service
public class ProductServices {

	@Autowired
	public ProductRepo productRepo;
	
	
	public Product saveProduct(Product product) {
		
		return productRepo.save(product);
	}

	public Product fetchProductById(Long id) {
		
		Optional<Product> product =
                productRepo.findById(id);

        if(!product.isPresent()) {
            throw new ProductNotFoundException("Product", "Id", id);
        }

        return  product.get();
	}

	public void deleteProductById(Long id) {
		
		Optional<Product> product =
                productRepo.findById(id);

        if(product.isPresent()) {
            
        	productRepo.deleteById(id);
        	System.out.println("deleted successfully");
        }
        else {
        	throw new ProductNotFoundException("Product", "Id", id);
        }
        
		
	}

	public Product updateProductDetails(Product product, Long id) {
		
		Product dbProduct = productRepo.findById(id).orElseThrow(
				() -> new ProductNotFoundException("Product", "Id", id)); 
		
		dbProduct.setProductName(product.getProductName());
		dbProduct.setPrice(product.getPrice());
		productRepo.save(dbProduct);
		return dbProduct;
	}

	public List<Product> fetchAllProduct() {
		
		return productRepo.findAll();
	}

	public void deleteAllProduct() {
		
	     productRepo.deleteAll();
	}

}