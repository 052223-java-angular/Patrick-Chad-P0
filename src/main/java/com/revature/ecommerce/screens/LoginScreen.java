
package com.revature.ecommerce.screens;


import java.util.Optional;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.ecommerce.daos.CartDAO;
import com.revature.ecommerce.daos.OrderDAO;
import com.revature.ecommerce.daos.ProductDAO;
import com.revature.ecommerce.daos.UserDAO;
import com.revature.ecommerce.models.Cart;
import com.revature.ecommerce.models.User;
import com.revature.ecommerce.services.CartService;
import com.revature.ecommerce.services.RouterService;
import com.revature.ecommerce.services.UserService;
import com.revature.ecommerce.utils.Session;
import com.revature.ecommerce.utils.Session;

public class LoginScreen implements IScreen{
    private final UserService userservice;
    private static LoginScreen instance;
    private static CartService cartservice = CartService.getInstance();
    private Session session;
   

    private LoginScreen(UserService userservice)
    {
        this.userservice = userservice;
        //this.session = session;
    }

    public static LoginScreen getInstance()
    {
       //System.out.println("In LoginScreen.getInstance()");
        if(instance == null)
        {
            //System.out.println("Instance null. Creating new instance");
            instance = new LoginScreen(UserService.getInstance());
        }

        return instance;
    }


    @Override
    public void start(Scanner scan) {
       

    }

    public void start(Scanner scan, Session session)
    {
        String username = ""; 
        String password = "";

        exit:
        {
            while(true)
            {
                //clearScreen();
                System.out.println("Login");
                username = getUsername(scan);

                if(username.equalsIgnoreCase("x"))
                {
                    break exit;
                }

                password = getPassword(scan);

                if(password.equalsIgnoreCase("x"))
                {
                    break exit;
                }


                Optional<User> user = userservice.callLookupUser(username);
                //System.out.println("User.isEmpty()? " + user.isEmpty());

                if(user.isEmpty())
                {
                    System.out.println("User Not Found. Press Enter to continue");
                    scan.nextLine();
                    continue;
                }
                else
                {
                    User usr = user.get();
                    if(BCrypt.checkpw(password, usr.getPassword()))
                    {
                       session.setSession(usr);
                       Cart cart = CartService.checkifCartExists(session.getCart_id());

                       if(cart == null)
                       {
                            //System.out.println("Cart is null");
                            cartservice.createCart(session);
                            session.setSession(usr);
                            CartService.addCartToDB(cartservice.getCart(), usr.getId());
                       }
                       else 
                       {
                          //System.out.println("Cart is not null");
                          cartservice.setCart(cart);
                          session.setCart_id(cartservice.getCartId());
                          session.setUsername(usr.getUsername());

                          

                       }
                       
                        
                        //System.out.println("Welcome to YourStore, " + usr.getUsername() + "!");
                        new RouterService().navigate("/user", scan, session);
                    }
                    else
                    {
                        System.out.println("Password is incorrect");
                        System.out.println("Press Enter to continue. . . ");
                        scan.nextLine();
                        continue;
                    }

                }



                break;


            }

        }

        

            
        
        
     
        

    }

    public String getUsername(Scanner scan)
    {
        System.out.println("In getUsername");
        String username = "";

        while(true)
        {
            System.out.print("Enter a username(Press x to cancel):");
            username = scan.nextLine();

            if(username.equalsIgnoreCase("x"))
            {
                return "x";
            }
            

            if(!userservice.isValidUsername(username))
            {
                
                //clearScreen();
                System.out.println("Username needs to be 8-20 characters long.");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            
            break;

        }

        return username;
    }

    public String getPassword(Scanner scan)
    {
        String password = "";
      while(true)
      {
        System.out.print("Enter a password (Press x to cancel)");
        password = scan.nextLine();

        if(password.equalsIgnoreCase("x"))
        {
            return "x";
        }

        if(!userservice.isValidPassword(password))
        {
            //clearScreen();
            System.out.println("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
            System.out.print("\nPress enter to continue...");
            scan.nextLine();
            continue;
        }
        break;
      }
      return password;
    }


   public Optional<User> lookupUserIdAndUsername(String username, String password)
   {
        Optional<User> user = userservice.getUserDAO().findUserByUsername(username);

        return user;
   }



    

    /*private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }*/
    
}
 