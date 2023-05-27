package com.revature.ecommerce.services;

import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.ecommerce.models.Cart;
import com.revature.ecommerce.models.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class CartService {
    private static Cart cart;

  public static void createCart()
  {
    String cart_id = UUID.randomUUID().toString();
    String hashed_cart_id = BCrypt.hashpw(cart_id, BCrypt.gensalt());
    cart = new Cart();
    cart.setId(hashed_cart_id);
  }

  public static String getCartId()
  {
    return cart.getId();
  }

  public static void setId(String user_id)
  {
     cart.setUser_id(user_id);
  }



    
}
