package com.cts.prod.management.repository;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.cts.prod.management.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
//	@Modifying
	@Transactional
//	@Query(value = "DELETE FROM product WHERE product_id = :productId", nativeQuery = true)
	void deleteByProductId(Integer productId);

	Product findAllByProductId(Integer productId);
	
	Date findBidEndDateByProductId(Integer productId);
}