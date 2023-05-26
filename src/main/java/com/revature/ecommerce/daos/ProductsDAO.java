package com.revature.ecommerce.daos;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.utils.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductsDAO implements CrudDAO<Product> {
    @Override
    public void update(Product obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void lookupUser(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lookupUser'");
    }

    
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
    
    
    public Optional<Product> lookupByProductName(String name) {
        Product product = new Product();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM products WHERE name=?";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, name);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));

                        return Optional.of(product);
                        //products.add(product);
                    }
                }
            }


        } catch(NoSuchElementException e){
            System.out.println("No such item found. Press Enter to continue....");
            
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void save(Product obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }


    public List<Product> lookupByCategory(String category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lookupByCategory'");
    }
}
