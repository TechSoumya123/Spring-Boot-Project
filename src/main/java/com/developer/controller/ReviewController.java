package com.developer.controller;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.model.Review;
import com.developer.service.ReviewService;

@RestController
@RequestMapping(path = { "/company/{companyId}" })
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping(path = { "/reviews" })
	public ResponseEntity<?> getAllReviews(@PathVariable("companyId") Long companyId) {
		try {
			var allReviews = reviewService.fetchAllReviews(companyId);
			return allReviews.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
					: ResponseEntity.status(HttpStatus.OK).body(allReviews);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@PostMapping(path = { "/review-create" })
	public ResponseEntity<String> createReview(@PathVariable("companyId") Long companyId, @RequestBody Review review) {
		try {
			return reviewService.createReviewByCompany(companyId, review)
					? ResponseEntity.status(HttpStatus.OK).body("Review created successfully ")
					: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not saved ");
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@GetMapping(path = { "/reviews/{reviewId}" })
	public ResponseEntity<Object> getReviewBy_SpecificReviewId(@PathVariable("companyId") Long companyId,
			@PathVariable("reviewId") Long reviewId) {
		try {
			Review review = reviewService.getReviewBy_SpecificReviewId(companyId, reviewId);
			return Objects.isNull(review) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No review found ")
					: ResponseEntity.status(HttpStatus.OK).body(review);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@PutMapping(path = { "/update-review/{reviewId}" })
	public ResponseEntity<?> updateReview(@PathVariable("companyId") Long companyId,
			@PathVariable("reviewId") Long reviewId, @RequestBody Review review) {
		try {
			return reviewService.updateReview(companyId, reviewId, review)
					? ResponseEntity.status(HttpStatus.OK).body("Review updated successfully")
					: ResponseEntity.status(HttpStatus.NOT_FOUND).body("No review found");
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@DeleteMapping(path = { "/delete-review/{reviewId}" })
	public ResponseEntity<String> deleteReview(@PathVariable("companyId") Long companyId,
			@PathVariable("reviewId") Long reviewId) {
		try {
			return reviewService.deleteReview(companyId, reviewId)
					? ResponseEntity.status(HttpStatus.OK).body("Review deleted successfully")
					: ResponseEntity.status(HttpStatus.NOT_FOUND).body("No review found");
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

}
