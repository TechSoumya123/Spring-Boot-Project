package com.developer.service;

import java.util.List;

import com.developer.model.Review;

public interface ReviewService {

	List<Review> fetchAllReviews(Long companyId);

	boolean createReviewByCompany(Long companyId, Review review);

	Review getReviewBy_SpecificReviewId(Long companyId, Long reviewId);

	boolean updateReview(Long companyId, Long reviewId, Review review);

	boolean deleteReview(Long companyId, Long reviewId);
}
