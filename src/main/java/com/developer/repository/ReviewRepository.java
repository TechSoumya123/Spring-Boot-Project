package com.developer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query(value = "select *  from review where company_id = :company_id", nativeQuery = true)
	List<Review> findAllReviewsByCompanyId(@Param("company_id") Long company_id);

}
