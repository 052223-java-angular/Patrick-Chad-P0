package com.revature.ecommerce.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.revature.ecommerce.daos.ReviewDAO;
import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.models.Review;
import com.revature.ecommerce.utils.Session;

public class ReviewService {
    ReviewDAO reviewDao = new ReviewDAO();
    Review review = new Review();

    /**
    *  Parameters: prod - Product - product selected to retrieve reviews
    *  Description: Displays reviews for selected product
    *  Return: A list of reviews
    *  Author: Chad Rotruck 
    */
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

    /**
    *  Parameters: session - Session - the user session.
    *  Description: Checks to see if user has already left a review for selected product
    *  Return: true if there is an existing review.
    *  Author: Chad Rotruck 
    */
    public boolean checkForExistingReview(Session session,Product prod){
        boolean reviewFound = reviewDao.checkForExistingReview(session, prod);
        //System.out.println("Review found: " + reviewFound);
        if (reviewFound){
            return true;
        }
        return false;
    }

    public void save(Review review){
        reviewDao.save(review);
    }
}
