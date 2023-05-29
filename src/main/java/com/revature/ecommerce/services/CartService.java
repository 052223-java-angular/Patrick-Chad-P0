package com.revature.ecommerce.services;

<<<<<<< HEAD
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
=======
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
    
>>>>>>> 03eb69f1b1d3ef7466309a68cb0872976903bc2e
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.revature.ecommerce.daos.CartDAO;
<<<<<<< HEAD
=======
import com.revature.ecommerce.daos.OrderDAO;
import com.revature.ecommerce.daos.ProductDAO;
>>>>>>> 03eb69f1b1d3ef7466309a68cb0872976903bc2e
import com.revature.ecommerce.models.Cart;
import com.revature.ecommerce.models.Order;
import com.revature.ecommerce.models.Product;
<<<<<<< HEAD
import com.revature.ecommerce.utils.ConnectionFactory;
=======
import com.revature.ecommerce.models.User;
>>>>>>> 03eb69f1b1d3ef7466309a68cb0872976903bc2e

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class CartService {
<<<<<<< HEAD
    private static Cart cart;
    private static CartDAO cartdao;

    private CartService(CartDAO cartdao)
    {
      this.cartdao = cartdao;
    }
=======
  private static final Logger logger = LogManager.getLogger(CartService.class);
  private static Cart cart;
  private static ProductDAO prodDao;
  private static OrderDAO orderDAO;
>>>>>>> 03eb69f1b1d3ef7466309a68cb0872976903bc2e

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
}
