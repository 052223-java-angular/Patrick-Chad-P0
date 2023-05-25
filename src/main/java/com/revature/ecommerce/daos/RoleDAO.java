package com.revature.ecommerce.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.revature.ecommerce.models.Role;
import com.revature.ecommerce.utils.ConnectionFactory;


public class RoleDAO {

    public Optional<Role> findByName(String name) {
        try(Connection conn = (Connection)ConnectionFactory.getInstance())
        {
            String sql = "SELECT * FROM roles WHERE name = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1, name);

                try(ResultSet rs = ps.executeQuery())
                {
                    if(rs.next())
                    {
                        Role role = new Role();
                        role.setId(rs.getString("id"));
                        role.setName(rs.getString("name"));
                        role.setUser_id(rs.getString("user_id"));
                        return Optional.of(role);
                    }
                }
            }
        }
        catch(SQLException e)
        {
            throw new RuntimeException("Cannot connect to the database");
        }
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException("Cannot find application.properties");
        }
        catch(IOException e)
        {
            throw new RuntimeException("Unable to load jdbc");
        }

        return Optional.empty();
    }
    
}
