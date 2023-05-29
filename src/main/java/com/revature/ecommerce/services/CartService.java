package com.revature.ecommerce.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.ecommerce.daos.CartDAO;
import com.revature.ecommerce.models.Cart;
import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.utils.ConnectionFactory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class CartService {
    private static Cart cart;
    private static CartDAO cartdao;

    private CartService(CartDAO cartdao)
    {
      this.cartdao = cartdao;
    }

  public static void createCart()
  {

    String cart_id = UUID.randomUUID().toString();
    String hashed_cart_id = BCrypt.hashpw(cart_id, BCrypt.gensalt());
    cart = new Cart();
    cart.setId(hashed_cart_id);
    System.out.println("Cart created");
  }

  public static Cart getCart()
  {
    return cart;
  }

  public static String getCartId()
  {
    return cart.getId();
  }

  public static void setId(String user_id)
  {
     cart.setUser_id(user_id);
  }

  public void addToCart(Product product, int quantity)
  {
      cartdao.addToCart(product, quantity);
  }

  public static CartService callCartServiceConstructor(CartDAO cartdao)
  {
      return new CartService(cartdao);
  }

  public static void addCartToDB(String id, String user_id)
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
