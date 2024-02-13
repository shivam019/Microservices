package com.shivam.reviewms.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long CompanyId);

    Boolean addReview(Review review, Long companyId);

    boolean updateReview(Long reviewId, Review review);

    Review getReview(Long reviewId);

    boolean deleteReview(Long reviewId);

}
