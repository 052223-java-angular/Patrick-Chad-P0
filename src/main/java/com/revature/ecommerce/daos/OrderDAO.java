package com.revature.ecommerce.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.revature.ecommerce.models.Order;
import com.revature.ecommerce.utils.ConnectionFactory;

public class OrderDAO implements CrudDAO<Order>{

    @Override
    public void save(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void update(Order obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Order obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<Order> lookupUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lookupUser'");
    }

    public Order saveOrder(Order order){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "INSERT INTO orders (id,order_date,user_id,cart_id) values (?,?,?,?)";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, order.getId());
                ps.setDate(2, order.getOrder_date());
                ps.setString(3, order.getUser_id());
                ps.setString(4, order.getCart_id());
                ps.executeUpdate();                
            }
            return order;
        }catch(SQLException e){
            throw new RuntimeException("Unable to connect to database. Error code: " + e.getMessage());
        } catch(IOException e){
            throw new RuntimeException("Unable to find application.properties");
        } catch(ClassNotFoundException e){
            throw new RuntimeException("Unable to load jdbc");
        }
    }
    
}
