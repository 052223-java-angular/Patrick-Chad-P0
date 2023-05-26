
package com.revature.ecommerce.screens;

import java.util.Scanner;

import com.revature.ecommerce.services.RouterService;
import com.revature.ecommerce.services.UserService;
import com.revature.ecommerce.utils.Session;

public class RegisterScreen implements IScreen{
    private final UserService userservice;
    private final RouterService routerservice;
    private Session session;
    private static RegisterScreen instance;
    
    private RegisterScreen(UserService userservice, RouterService routerservice)
    {
        this.userservice = userservice;
        this.routerservice = routerservice;
    }

    public static RegisterScreen getInstance()
    {
        if(instance == null)
        {
            instance = new RegisterScreen(UserService.getInstance(), new RouterService());
        }

        return instance;
    }


    

    @Override
    public void start(Scanner scan) {
        String username = "";
        String password = "";

        exit:{
            while(true)
            {
                clearScreen();
                System.out.println("Welcome to the register screen");
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


                break;
            }
            
            
        }

        userservice.register(username, password);
        
    }

    public String getUsername(Scanner scanner)
    {
        String username = "";

        while(true)
        {
            System.out.println("Enter a username: (or press x to quit)");
            username = scanner.nextLine();

            if(username.equalsIgnoreCase("x"))
            {
                System.out.println("Returning x");
                return "x";
            }

            if(!userservice.isValidUsername(username))
            {
                clearScreen();
                System.out.println("Username is not valid");
                System.out.println("Press Enter to continue. . .");
                scanner.nextLine();
                continue;
            }

            if(!userservice.isUniqueUsername(username))
            {
                clearScreen();
                System.out.println("Username is already taken. Please choose another one");
                System.out.println("Press Enter to continue. . .");
                scanner.nextLine();
                continue;
            }
            break;
        }

        return username;
       
    }

    public String getPassword(Scanner scan)
    {
        String password = "";
        String confirmPassword = "";

        while(true)
        {
            System.out.println("Enter a password (or press x to quit)");
            password = scan.nextLine();

            if(password.equalsIgnoreCase("x"))
            {
               return "x";
            }

            if (!userservice.isValidPassword(password)) {
                clearScreen();
                System.out.println("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            System.out.print("\nPlease confirm password (x to cancel): ");
            confirmPassword = scan.nextLine();

            if (confirmPassword.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!userservice.isSamePassword(password, confirmPassword)) {
                clearScreen();
                System.out.println("Passwords do not match");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            break;
        }

        
        
        return password;
    }

    

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
 