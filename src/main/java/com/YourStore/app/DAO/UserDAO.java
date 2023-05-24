package com.YourStore.app.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import com.YourStore.app.Model.User;
import com.YourStore.app.Utils.ConnectionFactory;

public class UserDAO implements CrudDAO<User>{
    /*private final ConnectionFactory conn;

    private UserDAO(ConnectionFactory conn)
    {
        this.conn = conn;
    }*/

    
    @Override
    public void update(User user){
        try(Connection conn = (Connection) ConnectionFactory.getInstance())
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


    
    
        
        
    


    

    



   
    



}
