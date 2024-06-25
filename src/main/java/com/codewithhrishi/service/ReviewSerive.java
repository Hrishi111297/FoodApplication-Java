package com.codewithhrishi.service;

import java.util.List;

import com.codewithhrishi.entity.Review;
import com.codewithhrishi.entity.UserData;
import com.codewithhrishi.exception.ReviewException;
import com.codewithhrishi.request.ReviewRequest;

public interface ReviewSerive {
	
    public Review submitReview(ReviewRequest review,UserData user);
    public void deleteReview(Long reviewId) throws ReviewException;
    public double calculateAverageRating(List<Review> reviews);
}
