package com.revature.ecommerce.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.revature.ecommerce.models.User;
import com.revature.ecommerce.utils.ConnectionFactory;

public class UserDAO implements CrudDAO<User>{

    @Override
    public void update(User user){
       // TODO Auto-generated method stub
       throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


    public Optional<User> findUserByUsername(String username)
    {
        
        try(Connection conn = (Connection) ConnectionFactory.getInstance())
        {
           String sql = "SELECT * FROM Users WHERE username = ?";

           try(PreparedStatement ps = conn.prepareStatement(sql))
           {
               ps.setString(1, username);
               
               try(ResultSet rs = ps.executeQuery())
               {
                  if(rs.next())
                  {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    return Optional.of(user);

                  }
                  else
                  {
                     return Optional.empty();
                  }

               }


           }
        }
        catch(SQLException e)
        {
            throw new RuntimeException("Not able to connect to the database");
        }
        catch(IOException e)
        {
            throw new RuntimeException("Cannot find application.properties");
        }
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException("Unable to load jdbc");
        }
    }


    @Override
    public void delete(User obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


    @Override
    public void lookupUser(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lookupUser'");
    }


    @Override
    public List<User> lookupProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lookupProducts'");
    }


    @Override
    public void save(User user) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection())
        {
            String sql = "INSERT INTO users (id, username, password) VALUES (?, ? ,? );";

            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1, UUID.randomUUID().toString());
                ps.setString(2, user.getUsername());
                ps.setString(3, user.getPassword());
                ps.executeUpdate();
            }
            
        }
        catch(SQLException e)
        {
            throw new RuntimeException("Not able to connect to the database");
        }
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException("Unable to load jdbc");
        }
        catch(IOException e)
        {
            throw new RuntimeException("Cannot find application.properties");
        }
    }


    @Override
    public Optional<User> lookupByProductName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lookupByProductName'");
    }
}
