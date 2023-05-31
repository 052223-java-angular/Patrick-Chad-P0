package com.revature.ecommerce.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.revature.ecommerce.models.Category;
import com.revature.ecommerce.utils.ConnectionFactory;

public class CategoryDAO implements CrudDAO<Category> {

    @Override
    public void save(Category obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(Category obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Category obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<Category>lookupUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lookupUser'");
    }

    /**
    *  Parameters: none
    *  Description: gets a list of all categories from db
    *  Return: A list of categories
    *  Author: Chad Rotruck 
    */
    public List<Category> getAllCategories(){
        List<Category> cats = new LinkedList<Category>();
        //get connection
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            //set prepared statement

            String sql = "SELECT * FROM categories";            
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                //execute query
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        //create list of categories
                        Category category = new Category();
                        category.setId(rs.getString("id"));
                        category.setName(rs.getString("name"));

                        cats.add(category);
                    }
                    return cats; 
                }
            }
        } catch(SQLException e){
            throw new RuntimeException("Unable to connect to database.");
        } catch(IOException e){
            throw new RuntimeException("Unable to find application.properties");
        } catch(ClassNotFoundException e){
            throw new RuntimeException("Unable to load jdbc");
        }
    }
    
}
