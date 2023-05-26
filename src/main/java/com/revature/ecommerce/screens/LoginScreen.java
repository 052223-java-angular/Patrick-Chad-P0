
package com.revature.ecommerce.screens;

import java.util.Optional;
import java.util.Scanner;

import com.revature.ecommerce.models.User;
import com.revature.ecommerce.services.UserService;

public class LoginScreen implements IScreen{
    private final UserService userservice;
    private static LoginScreen instance;

    private LoginScreen(UserService userservice)
    {
        this.userservice = userservice;
    }

    public static LoginScreen getInstance()
    {
        if(instance == null)
        {
            instance = new LoginScreen(UserService.getInstance());
        }

        return instance;
    }


    @Override
    public void start(Scanner scan) {
       String username = ""; 
       String password = "";

        exit:
        {
            while(true)
            {
                clearScreen();
                System.out.println("Login:");

                username = getUsername(scan);

                if(username.equalsIgnoreCase("x"))
                {
                    break exit;
                }

                password = getPassword(scan);

                if(username.equalsIgnoreCase("x"))
                {
                    break exit;
                }

                break;

            }





        }

        

       


    }

    public String getUsername(Scanner scan)
    {
        String username = "";

        while(true)
        {
            System.out.print("Enter a username(Press x to cancel):");
            username = scan.nextLine();

            

            if(!userservice.isValidUsername(username))
            {
                clearScreen();
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

        

        if(!userservice.isValidPassword(password))
        {
            clearScreen();
            System.out.println("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
            System.out.print("\nPress enter to continue...");
            scan.nextLine();
            continue;
        }
        break;
      }
      return password;
    }


   /*public Optional<User> lookupUserIdAndUsername(String username, String password)
   {
        
   }*/



    

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}
 