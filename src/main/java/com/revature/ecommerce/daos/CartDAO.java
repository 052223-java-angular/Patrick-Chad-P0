package com.revature.ecommerce.daos;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.ecommerce.models.Cart;
import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.utils.ConnectionFactory;

public class CartDAO implements CrudDAO<Cart>{

    @Override
    public void save(Cart obj) {
        
    }

    @Override
    public void update(Cart obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Cart obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<Cart> lookupUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lookupUser'");
    }

    /*
    *   Parameters: name - String - This is the name of the product to search for.

        Purpose: this routine is used to query the database to get a specific item by name.

        Return: This routine will return an Optional<Product>.
    */
    public Cart getCart(String userId){
        //connect to db
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            // prepared sql statement
            String sql = "SELECT * FROM carts WHERE user_id=?";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, userId);
                //execute query
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        // create instance of cart and return cart.
                        Cart cart = new Cart();
                        cart.setId(rs.getString("id"));
                        cart.setUser_id(rs.getString("user_id"));

                        return cart;
                    }
                }
            }            
        } catch(SQLException e){
            throw new RuntimeException("Unable to connect to database. Error code: " + e.getMessage());
        } catch(IOException e){
            throw new RuntimeException("Unable to find application.properties");
        } catch(ClassNotFoundException e){
            throw new RuntimeException("Unable to load jdbc");
        }
        //return null if no record found.
        return null;
    }
   
    public static List<Product> getCartItems(String CartId){
        List<Optional<Product>> productsOpt = new ArrayList<Optional<Product>>();
        List<Product> listProducts = new ArrayList<Product>();
        //create connection to db
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * from cartitems WHERE cart_id=?";
            //create preparedStatement
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, CartId);
                //execute query
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        product.setQty_on_hand(rs.getInt("qty_on_hand"));

                        productsOpt.add(Optional.of(product));
                    }
                }
                if (productsOpt.isEmpty()){
                    return null; 
                } else {
                    int i = 0;
                    for (Optional<Product> productOpt : productsOpt) {
                        productOpt = productsOpt.get(i++);
                        Product prod = productOpt.get();
                        listProducts.add(prod);
                    } 
        
                    return listProducts;  
                }        
            }
        } catch(SQLException e){
            throw new RuntimeException("Unable to connect to database. Error code: " + e.getMessage());
        } catch(IOException e){
            throw new RuntimeException("Unable to find application.properties");
        } catch(ClassNotFoundException e){
            throw new RuntimeException("Unable to load jdbc");
        }
    }
}
