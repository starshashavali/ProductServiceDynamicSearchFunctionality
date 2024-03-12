package com.tcs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tcs.entity.Product;
import com.tcs.repo.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	public String saveProduct(Product product) {
		productRepo.save(product);
		return "saved success";
	}

	public List<Product> getAllProductStartingPriceToEndingPrice(Double low, Double high) {
		return productRepo.findByPriceBetween(low, high);

	}

	public List<Product> getProductHighestRating(Double rating) {
		return productRepo.findByRatingGreaterThanEqual(rating);
	}

	public List<Product> getByProductNameAndCategoryAndPrice(String name, String category, Double price) {
		return productRepo.findByProductNameAndCategoryAndPrice(name, category, price);
	}

	public List<Product> findProductswithSorting(String field) {
		return productRepo.findAll(Sort.by(Sort.Direction.ASC, field));

	}

	public List<Product> getRatingGreatenEqualTo(Double rating) {
		return productRepo.findByRatingGreaterThanEqual(rating);
	}

	public List<Product> getSearchKeyword(String keyword) {
		return productRepo.search(keyword);
	}

	public List<Product> getSearchByTwofields(String keyword1, String keyword2) {
		List<Product> allProducts = productRepo.findAll();

		return allProducts.stream()
				.filter(product -> containsIgnoreCase(product.getProductName(), keyword1)
						|| containsIgnoreCase(product.getCategory(), keyword1)
						|| containsIgnoreCase(String.valueOf(product.getPrice()), keyword1)
						|| containsIgnoreCase(String.valueOf(product.getRating()), keyword1)
						|| containsIgnoreCase(String.valueOf(product.isStatus()), keyword1))
				.filter(product -> containsIgnoreCase(product.getProductName(), keyword2)
						|| containsIgnoreCase(product.getCategory(), keyword2)
						|| containsIgnoreCase(String.valueOf(product.getPrice()), keyword2)
						|| containsIgnoreCase(String.valueOf(product.getRating()), keyword2)
						|| containsIgnoreCase(String.valueOf(product.isStatus()), keyword2))
				.collect(Collectors.toList());
	}

	public List<Product> getSearchByTwofieldsWithPriceRange(String keyword1, String keyword2, Double lowPrice, Double highPrice) {
		List<Product> allProducts = productRepo.findAll();

		return allProducts.stream()
				.filter(product -> containsIgnoreCase(product.getProductName(), keyword1)
						|| containsIgnoreCase(product.getCategory(), keyword1)
						|| containsIgnoreCase(String.valueOf(product.getPrice()), keyword1)
						|| containsIgnoreCase(String.valueOf(product.getRating()), keyword1)
						|| containsIgnoreCase(String.valueOf(product.isStatus()), keyword1))
				.filter(product -> containsIgnoreCase(product.getProductName(), keyword2)
						|| containsIgnoreCase(product.getCategory(), keyword2)
						|| containsIgnoreCase(String.valueOf(product.getPrice()), keyword2)
						|| containsIgnoreCase(String.valueOf(product.getRating()), keyword2)
						|| containsIgnoreCase(String.valueOf(product.isStatus()), keyword2))
				.filter(product -> (lowPrice == null || product.getPrice() >= lowPrice)
						&& (highPrice == null || product.getPrice() <= highPrice))
				.collect(Collectors.toList());
	}

	private boolean containsIgnoreCase(String source, String target) {
		return source.toLowerCase().contains(target.toLowerCase());
	}

}
