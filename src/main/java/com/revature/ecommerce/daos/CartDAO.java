package com.revature.ecommerce.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import com.revature.ecommerce.models.Cart;
import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.services.CartService;
import com.revature.ecommerce.utils.ConnectionFactory;

public class CartDAO implements CrudDAO<Cart>
{

    @Override
    public void save(Cart obj) {
        //TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented Method 'save'");
    }

    @Override
    public void update(Cart cart) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented Method 'update'");
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

    public void addToCart(Product product, int quantity)
    {
        System.out.println("In CartDAO.updateCartItems()");
       
        try(Connection conn = ConnectionFactory.getInstance().getConnection())
        {
            String sql = "INSERT INTO cartitems(id, qty, price, cart_id, product_id) VALUES (?,?,?,?,?)";

            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1, UUID.randomUUID().toString());
                ps.setInt(2, quantity);
                ps.setDouble(3, product.getPrice());
                ps.setString(4, CartService.getCartId());
                ps.setString(5,product.getId());
                ps.executeUpdate();
               
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("SQLException caught at CartDAO.update()");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException("ClassNotFoundException caught at CartDAO.update()");
        }
        catch(IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException("IOException caught at CartDAO.update()");
        }
    }

    public void addCartToDB(Cart cart, String user_id)
    {
        try(Connection conn = ConnectionFactory.getInstance().getConnection())
        {
            String sql = "INSERT INTO cart(id, user_id) VALUES (?,?)";
            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1, CartService.getCartId());
                ps.setString(2, user_id);
                ps.executeUpdate();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("SQLException caught at CartDAO.update()");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException("ClassNotFoundException caught at CartDAO.update()");
        }
        catch(IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException("IOException caught at CartDAO.update()");
        }
    }
}

    







   
   
    

