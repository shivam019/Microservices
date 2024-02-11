package com.shivam.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")

//GET /reviews?companyId={companyId}  // ? --- Query_Parameter
// POST /reviews?companyId={companyId}  // ? --- Query Parameter
// GET /reviews/{reviewId}  // {reviewId} --> Path Parameter
// PUT /reviews/{reviewId}
// DELETE /reviews/{reviewId

public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

 //Get All Reviews
@GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review){

        boolean isReviewSaved =  reviewService.addReview(review);

     if(isReviewSaved) {
         return new ResponseEntity<>("Review Added Sucessfully", HttpStatus.OK);

     } else  {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);

     }
    }

    //Get review by ID

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        return  new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    //UPDATE REIVEW
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable long reviewId, @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(reviewId, review);

        if(isReviewUpdated) {
            return new ResponseEntity<>("Review Updated Sucessfully", HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);

        }

    }


    //DELETE REVIEW
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable  Long reviewId){

        boolean isReviewDeleted = reviewService.deleteReview(reviewId);

        if(isReviewDeleted) {
            return new ResponseEntity<>("Review Deleted Sucessfully", HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);

        }

    }

}
