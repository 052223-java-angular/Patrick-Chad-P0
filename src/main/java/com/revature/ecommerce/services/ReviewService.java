package com.revature.ecommerce.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.revature.ecommerce.daos.ReviewDAO;
import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.models.Review;

public class ReviewService {
    ReviewDAO reviewDao = new ReviewDAO();
    Review review = new Review();

    public List<Review> getAllReviews(Product prod){
        List<Optional<Review>> optReviews = reviewDao.getReviews(prod.getId());
        List<Review> reviews = new LinkedList<Review>();
        
        if (optReviews.isEmpty()){
            return null; 
        } else {
            int i = 0;
            for (Optional<Review> optReview : optReviews) {
                optReview = optReviews.get(i++);
                review = optReview.get();
                reviews.add(review);
            } 

            return reviews;  
        }        
    }

    public void save(Review review){
        reviewDao.save(review);
    }
}
