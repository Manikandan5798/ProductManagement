package com.product;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.product.model.Product;
import com.product.repository.ProductRepo;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductAppRepoTest {
	
	@Autowired
	public ProductRepo productRepo;
	
	//Junit test for saveProduct
	
	@Test
	@Order(1)
	public void saveProductTest() {
		
		Product prod = new Product();
		prod.setProductId(1L);
		prod.setProductName("Earbuds");
		prod.setPrice(2500L);
		productRepo.save(prod);
		System.out.println(productRepo);
		Assertions.assertThat(prod.getProductId()).isGreaterThan(0);
	}
	
	//Junit test for getProduct

	@Test
	@Order(2)
	public void getProductTest() {

		Product prod = new Product();
		prod.setProductId(2L);
		prod.setProductName("Earbuds11");
		prod.setPrice(1500L);
		productRepo.save(prod);
		Product prod1 = productRepo.findById(2L).get();
		Assertions.assertThat(prod1.getProductId()).isEqualTo(2);
	}
	
	
	//Junit test for updateProduct
	
	@Test
	@Order(3)
	public void updateProductTest() {

		Product prod = new Product(3L,"Earbuds3",1000L);
		productRepo.save(prod);
		System.out.println(productRepo);
		Product prod1 = productRepo.findById(3L).get();
		
		prod1.setProductName("One Plus");
		Product updatedProduct = productRepo.save(prod1);
		Assertions.assertThat(updatedProduct.getProductName()).isEqualTo("One Plus");
	}
	
	//Junit test for deleteProduct
	
	@Test
	@Order(4)
	public void deleteProductTest() {

		Product prod = new Product(4L,"Earbuds5",2500L);
		productRepo.save(prod);
		Product prod1 = productRepo.findById(4L).get();
		
		productRepo.delete(prod1);
		Product prod2 = null;
		Optional<Product> oProd = productRepo.findById(4L);
		if(oProd.isPresent()) {
			prod2 = oProd.get();
		}
		
		Assertions.assertThat(prod2).isNull();
	}
	
	
}
