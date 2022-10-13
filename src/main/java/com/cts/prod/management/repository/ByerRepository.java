package com.cts.prod.management.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cts.prod.management.entity.Byerinfo;

public interface ByerRepository extends CrudRepository<Byerinfo, Integer> {

	@Query(value = "select * from byerinfo where email= :buyerEmailld and product_id= :productId", nativeQuery = true)
	Byerinfo findAllByProductIdAndEmail(int productId, String buyerEmailld);
	
	@Query(value = "select * from byerinfo where product_id= :productId order by bid_amount desc", nativeQuery = true)
	List<Byerinfo> findAllByProductId(int productId);

}