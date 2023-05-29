package com.revature.ecommerce.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
    
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.revature.ecommerce.daos.CartDAO;
import com.revature.ecommerce.daos.OrderDAO;
import com.revature.ecommerce.daos.ProductDAO;
import com.revature.ecommerce.models.Cart;
import com.revature.ecommerce.models.Order;
import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartService {
  private static final Logger logger = LogManager.getLogger(CartService.class);
  private static Cart cart;
  private static ProductDAO prodDao;
  private static OrderDAO orderDAO;

  public static void createCart()
  {

    String cart_id = UUID.randomUUID().toString();
    String hashed_cart_id = BCrypt.hashpw(cart_id, BCrypt.gensalt());
    cart = new Cart();
    cart.setId(hashed_cart_id);
    logger.info("Cart created");
    //System.out.println("Cart created");
  }

  public static String getCartId()
  {
    return cart.getId();
  }

  public static void setId(String user_id)
  {
    cart.setUser_id(user_id);
  }

  public Order Checkout(Scanner scan, Cart cart, User user){
    logger.info("Checkout Process");
    Order order = new Order();
    String input = "";

    //get products associated with cart_id
    List<Product> cartProducts = new ArrayList<Product>();
    cartProducts = CartDAO.getCartItems(cart.getId());

    //create total
    Double total = 0.0;
    for (Product product : cartProducts) {
      total += product.getPrice();
    }

    //confirm purchase
    System.out.print("Complete Purchase for $" + total + "? (y/n)");
    input = scan.nextLine();
    cartInput:{
      while(true){
        switch(input.toLowerCase()){
          case "y":
            //save order
            
            Date orderDate = new Date(0); // getting todays date
            order.setId(UUID.randomUUID().toString());
            order.setCart_id(cart.getId());
            order.setUser_id(cart.getUser_id());
            order.setOrder_date(orderDate);

            //update qty_on_hand for each item
            for (Product product : cartProducts) {
              prodDao.updateCartQtys(product);
            }

            //create order
            order = orderDAO.saveOrder(order);

            System.out.print("Order placed. Press enter to continue.....");

            //create new cart for customer
            createCart();
            //addCartToDB(cart,user.getId());
            scan.nextLine();
            break cartInput;
          case "n":
            //rejected
            break cartInput;
          default:
            break ;
        }
      }
    }
    return order;
  }
}
