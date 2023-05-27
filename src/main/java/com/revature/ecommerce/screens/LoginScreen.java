
package com.revature.ecommerce.screens;


import java.util.Scanner;


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
        System.out.println("In LoginScreen.getInstance()");
        if(instance == null)
        {
            System.out.println("Instance null. Creating new instance");
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

                if(username.equals("x"))
                {
                    System.out.println("About to break");
                    break exit;
                }

                System.out.println("Calling getpassword");
                password = getPassword(scan);

                if(password.equals("x"))
                {  
                    System.out.println("About to break");
                    break exit;
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
 