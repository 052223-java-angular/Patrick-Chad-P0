package com.revature.ecommerce.services;

import java.util.Scanner;
    
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
    System.out.println("Cart created");
  }

  public static String getCartId()
  {
    return cart.getId();
  }

  public static void setId(String user_id)
  {
     cart.setUser_id(user_id);
  }

    public static void ProcessOrder(Scanner scan, String user_id){
        //get user's cart_id
        //get products associated with cart_id
        //create total
        //confirm purchase
        //update qty_on_hand for each item
        //create order
    }

    
}
