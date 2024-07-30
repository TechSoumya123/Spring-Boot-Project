package com.developer.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.developer.model.Review;
import com.developer.repository.ReviewRepository;
import com.developer.service.CompanyService;
import com.developer.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;

	private final CompanyService companyService;

	public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
		this.reviewRepository = reviewRepository;
		this.companyService = companyService;
	}

	@Override
	public List<Review> fetchAllReviews(Long companyId) {
		return reviewRepository.findAllReviewsByCompanyId(companyId);
	}

	@Override
	public boolean createReviewByCompany(Long companyId, Review review) {
		var company = companyService.getCompanyById(companyId);
		if (Objects.nonNull(company)) {
			review.setCompany(company);
			reviewRepository.save(review);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Review getReviewBy_SpecificReviewId(Long companyId, Long reviewId) {
		var okreview = reviewRepository.findById(reviewId).orElse(null);
		return Objects.nonNull(okreview) && Objects.nonNull(companyService.getCompanyById(companyId)) ? okreview : null;
	}

	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review review) {
		var okReview = reviewRepository.findById(reviewId).orElse(null);
		var okCompany = companyService.getCompanyById(companyId);
		if (Objects.nonNull(okReview) & Objects.nonNull(okCompany)) {
			okReview.setTitle(review.getTitle());
			okReview.setDescription(review.getDescription());
			okReview.setRating(review.getRating());
			okReview.setCompany(okCompany);
			reviewRepository.save(okReview);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteReview(Long companyId, Long reviewId) {
		var okReview = reviewRepository.findById(reviewId).orElse(null);
		var okCompany = companyService.getCompanyById(companyId);
		if (Objects.nonNull(okReview) & Objects.nonNull(okCompany)) {
			reviewRepository.deleteById(reviewId);
			return true;
		}
		return false;
	}

}
