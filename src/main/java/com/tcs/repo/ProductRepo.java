package com.tcs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tcs.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	List<Product> findByPriceBetween(Double startingPrice, Double endPrice);

	List<Product> findByRatingGreaterThanEqual(double rating);

    List<Product> findByProductNameAndCategoryAndPrice(String name,String category,Double price);
    
    
    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.productName) LIKE %:keyword% OR " +
            "LOWER(p.category) LIKE %:keyword% OR " +
            "CAST(p.price AS STRING) LIKE %:keyword% OR " +
            "CAST(p.rating AS STRING) LIKE %:keyword% OR " +
            "CAST(p.status AS STRING) LIKE %:keyword%")
     List<Product> search(@Param("keyword") String keyword);
    
    
    
		/*
		 * @Query("SELECT p FROM Product p WHERE " +
		 * "(LOWER(p.productName) LIKE %:keyword1% OR " +
		 * " LOWER(p.category) LIKE %:keyword1% OR " +
		 * " CAST(p.price AS STRING) LIKE %:keyword1% OR " +
		 * " CAST(p.rating AS STRING) LIKE %:keyword1% OR " +
		 * " CAST(p.status AS STRING) LIKE %:keyword1%) AND " +
		 * "(LOWER(p.productName) LIKE %:keyword2% OR " +
		 * " LOWER(p.category) LIKE %:keyword2% OR " +
		 * " CAST(p.price AS STRING) LIKE %:keyword2% OR " +
		 * " CAST(p.rating AS STRING) LIKE %:keyword2% OR " +
		 * " CAST(p.status AS STRING) LIKE %:keyword2%)") List<Product>
		 * searchTwofields(@Param("keyword1") String keyword1, @Param("keyword2") String
		 * keyword2);
		 * 
		 */
   

}
