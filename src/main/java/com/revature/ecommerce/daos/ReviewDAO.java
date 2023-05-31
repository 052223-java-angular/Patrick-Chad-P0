package com.revature.ecommerce.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.revature.ecommerce.models.Review;
import com.revature.ecommerce.utils.ConnectionFactory;

public class ReviewDAO implements CrudDAO<Review> {

    @Override
    public void save(Review obj) {
        //save review;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "INSERT INTO reviews (id,rating,comment,user_id,product_id) VALUES (?,?,?,?,?,?)";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, UUID.randomUUID().toString());
                ps.setInt(1, obj.getRating());
                ps.setString(3, obj.getComment());
                ps.setString(4, obj.getUser_id());
                ps.setString(5, obj.getProduct_id());

                //execute query
                ps.executeUpdate();
            }
        } catch(SQLException e){
            throw new RuntimeException("Unable to connect to database.");
        } catch(IOException e){
            throw new RuntimeException("Unable to find application.properties");
        } catch(ClassNotFoundException e){
            throw new RuntimeException("Unable to load jdbc");
        }
    }

    @Override
    public void update(Review obj) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Review obj) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<Review> lookupUser(String username) {
        throw new UnsupportedOperationException("Unimplemented method 'lookupUser'");
    }

    public List<Optional<Review>> getReviews(String product_id){
        List<Optional<Review>> optReviews= new LinkedList<Optional<Review>>();

        //connect to database
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            //prepare the prepared statement
            String sql = "SELECT * FROM reviews WHERE product_id=?";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, product_id);
                //execute query
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        Review review = new Review();
                        review.setId(rs.getString("id"));
                        review.setRating(rs.getInt("rating"));
                        review.setComment(rs.getString("comment"));
                        review.setUser_id(rs.getString("user_id"));
                        review.setProduct_id(rs.getString("product_id"));

                        optReviews.add(Optional.of(review));
                    }
                }
            }
            return optReviews;
        } catch(SQLException e){
            throw new RuntimeException("Unable to connect to database.");
        } catch(IOException e){
            throw new RuntimeException("Unable to find application.properties");
        } catch(ClassNotFoundException e){
            throw new RuntimeException("Unable to load jdbc");
        }
    }
    
}
