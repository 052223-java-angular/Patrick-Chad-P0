package com.revature.YourStore.DAOs;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.YourStore.Models.Product;
import com.revature.YourStore.Utils.ConnectionFactory;

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

    @Override
    public List<Product> lookupProducts() {
        List<Product> products = new LinkedList<Product>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM products";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
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
    
    @Override
    public Optional<Product> lookupByProductID(String id) {
        Product product = new Product();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM product WHERE name=?";

            
        } catch(SQLException e){
            throw new RuntimeException("Unable to connect to database.");
        } catch(IOException e){
            throw new RuntimeException("Unable to find application.properties");
        } catch(ClassNotFoundException e){
            throw new RuntimeException("Unable to load jdbc");
        }
        return Optional.empty();
    }
}
