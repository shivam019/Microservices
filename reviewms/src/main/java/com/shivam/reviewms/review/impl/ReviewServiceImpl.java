package com.shivam.reviewms.review.impl;


import com.shivam.reviewms.review.Review;
import com.shivam.reviewms.review.ReviewRepository;
import com.shivam.reviewms.review.ReviewService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {

private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


@Override
    public Review getReview(Long reviewId){
       return reviewRepository.findById(reviewId).orElse(null);

    }

@Override
    public boolean updateReview(Long reviewId, Review updatedReview){
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(reviewId != null) {

            review.setTitle(updatedReview.getTitle());
            review.setRating(updatedReview.getRating());
            review.setDescription(updatedReview.getDescription());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(updatedReview);
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if(review != null) {
            reviewRepository.delete(review);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Boolean addReview(Review review) {
        if(review != null){
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }
}
