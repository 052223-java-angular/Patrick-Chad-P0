package com.revature.ecommerce.daos;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.utils.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO implements CrudDAO<Product> {
    @Override
    public void update(Product obj) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void lookupUser(String username, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'lookupUser'");
    }

    
    /*
    *   Parameters: none

        Purpose: this routine is used to query the database to get a list of items.

        Return: This routine will return a List<Product>.
    */
    public List<Product> lookupProducts() {
        List<Product> products = new LinkedList<Product>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM products";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        product.setQty_on_hand(rs.getInt("qty_on_hand"));

                        products.add(product);
                    }
                }
            }
            
            return products;
        } catch(SQLException e){
            throw new RuntimeException("Unable to connect to database.");
        } catch(IOException e){
            throw new RuntimeException("Unable to find application.properties");
        } catch(ClassNotFoundException e){
            throw new RuntimeException("Unable to load jdbc");
        }
    }
    
    
    /*
    *   Parameters: name - String - This is the name of the product to search for.

        Purpose: this routine is used to query the database to get a specific item by name.

        Return: This routine will return an Optional<Product>.
    */
    public Optional<Product> lookupByProductName(String name) {
        

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM products WHERE name=?";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, name);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        product.setQty_on_hand(rs.getInt("qty_on_hand"));

                        return Optional.of(product);
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
        
        return Optional.empty();
    }

    @Override
    public void delete(Product obj) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void save(Product obj) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }


    /*
    *   Parameters: category - String - Name of the selected category that will be used in the query

        Purpose: This routine will pull data based on the selected category and return a list of products.

        Return: This routine will return an Optional<Product> List.
    */
    public List<Optional<Product>> lookupByCategory(String category) {
        // connect to database
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM products WHERE category_id=?";
            List<Optional<Product>> prods= new LinkedList<Optional<Product>>();
            // create prepared statement
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, category);
                // execute statement
                try(ResultSet rs = ps.executeQuery()){
                    // add item to list
                    while(rs.next()){
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        product.setQty_on_hand(rs.getInt("qty_on_hand"));

                        prods.add(Optional.of(product));
                    }
                }
                return prods;
            }            
        } catch(SQLException e){
            throw new RuntimeException("Unable to connect to database. Error code: " + e.getMessage());
        } catch(IOException e){
            throw new RuntimeException("Unable to find application.properties");
        } catch(ClassNotFoundException e){
            throw new RuntimeException("Unable to load jdbc");
        }
    }

    /*
    *   Parameters: min - double - used to determine minumun bound of range
                    max - double - used to determine maximum bound of range

        Purpose: this routine is used to query the database to get a list of products
                    based on a price range set by the min and max parameters. If no
                    products exist in that price range, routine should return an empty list.

        Return: This routine will return an Optional<Product> List.
    */
    public List<Optional<Product>> lookupByPriceRange(double min, double max) {
        // connect to database
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM products WHERE price>=? AND price<=?";
            List<Optional<Product>> prods= new LinkedList<Optional<Product>>();

            // create prepared statement
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setDouble(1, min);
                ps.setDouble(2, max);

                // execute statement
                try(ResultSet rs = ps.executeQuery()){
                    
                    // add item to list
                    while(rs.next()){
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        product.setQty_on_hand(rs.getInt("qty_on_hand"));

                        prods.add(Optional.of(product));
                    }
                }
                return prods;
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
