package com.revature.ecommerce.services;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
    
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.revature.ecommerce.daos.CartDAO;
import com.revature.ecommerce.daos.OrderDAO;
import com.revature.ecommerce.daos.ProductDAO;
import com.revature.ecommerce.daos.UserDAO;
import com.revature.ecommerce.models.Cart;
import com.revature.ecommerce.models.Order;
import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@AllArgsConstructor
public class CartService {
  private static final Logger logger = LogManager.getLogger(CartService.class);
  private static Cart cart;
  private static CartDAO cartdao;
  private static ProductDAO prodDao;
  private static OrderDAO orderDAO;
  

  private CartService(ProductDAO prodDao, OrderDAO orderDAO, CartDAO cartdao)
  {
    this.prodDao = prodDao;
    this.orderDAO = orderDAO;
    this.cartdao = cartdao;
  }
  

  public static void createCart()
  {

    String cart_id = UUID.randomUUID().toString();
    String hashed_cart_id = BCrypt.hashpw(cart_id, BCrypt.gensalt());
    cart = new Cart();
    cart.setId(hashed_cart_id);

    logger.info("Cart created");
    //System.out.println("Cart created");
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

  public Order Checkout(Scanner scan, Cart cart){
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

  public void addToCart(Product product, int quantity)
  {
     cartdao.addToCart(product, quantity);
  }

public static CartService callCartServiceConstructor(ProductDAO prodDao, OrderDAO orderdao, CartDAO cartdao) {
      return new CartService(prodDao, orderdao, cartdao);
}

public static void addCartToDB(Cart cart, String user_id)
{
    cartdao.addCartToDB(cart, user_id);
}


public static List<Product> callGetCartItems(String cart_id)
{
   return CartDAO.getCartItems(cart_id);
}

public static void deleteFromCart(String name)
{
  
   
  
      cartdao.deleteFromCart(name);
      
   
   
      //new RouterService().navigate("/products", new Scanner(System.in))
}

public static void callUpdateQuantity(String name, int quantity)
{
    cartdao.updateQuantity(name, quantity);
}

}
