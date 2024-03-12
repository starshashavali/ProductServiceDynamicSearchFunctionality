package com.tcs.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.entity.Product;
import com.tcs.exception.PricenotFoundException;
import com.tcs.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		String status = productService.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(status);
	}

	@GetMapping("/findByPrice/{low}/{high}")
	public ResponseEntity<?> findPriceBetweenLowToHigh(@PathVariable Double low, @PathVariable Double high) {
		List<Product> products = productService.getAllProductStartingPriceToEndingPrice(low, high);
		if (products != null) {
			return ResponseEntity.status(HttpStatus.OK).body(products);
		} else {
			throw new PricenotFoundException(" Product not found Within" + low + " price range..." + high + "price");
		}
	}

	@GetMapping("/findHighestrating/{rating}")
	public ResponseEntity<?> findHigestRating(@PathVariable Double rating) {
		List<Product> ratingStatus = productService.getProductHighestRating(rating);
		return ResponseEntity.status(HttpStatus.OK).body(ratingStatus);
	}

	@GetMapping("/searchByAttributes/{name}/{category}/{price}")
	public ResponseEntity<?> getByProductNameAndCategoryAndPrice(@PathVariable String name,
			@PathVariable String category, @PathVariable Double price) {
		List<Product> products = productService.getByProductNameAndCategoryAndPrice(name, category, price);

		if (products != null && !products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(products);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found for the specified attributes.");
		}
		
	}
		@GetMapping("/sortByField/{keyword}")
		public ResponseEntity<?> sortByfield(@PathVariable String keyword){
			List<Product> sortingStatus = productService.findProductswithSorting(keyword);
			return ResponseEntity.status(HttpStatus.OK).body(sortingStatus);

		}
		
		@GetMapping("/search/{keyword}")
		public ResponseEntity<?> searchByfield(@PathVariable String keyword){
			List<Product> status = productService.getSearchKeyword(keyword);
			return ResponseEntity.status(HttpStatus.OK).body(status);

		}
		
		@GetMapping("/search/{keyword1}/{keyword2}")
		public ResponseEntity<?> searchTwoByfield(@PathVariable String keyword1,
				@PathVariable String keyword2){
			List<Product> status = productService.getSearchByTwofields(keyword1, keyword2);
			return ResponseEntity.status(HttpStatus.OK).body(status);

		}
		@GetMapping("/search/{keyword1}/{keyword2}/{low}/{high}")
		public ResponseEntity<?> searchTwofieldsWithPriceRange(@PathVariable String keyword1,
				@PathVariable String keyword2,
				@PathVariable  Double low,
				@PathVariable  Double high){
			List<Product> rangeStatus = productService.getSearchByTwofieldsWithPriceRange(keyword1, keyword2, low, high);
			
			return ResponseEntity.status(HttpStatus.OK).body(rangeStatus);
		}
		
		
		
		
		
	}

