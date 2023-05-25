package com.revature.ecommerce.screens;

import java.util.Scanner;

import com.revature.ecommerce.services.RouterService;
import com.revature.ecommerce.services.UserService;
import com.revature.ecommerce.utils.Session;

public class RegisterScreen implements IScreen{
    private final UserService userservice;
    private final RouterService routerservice;
    private Session session;
    @Override
    public void start(Scanner scan) {
        String username = "";
        String password = "";

        exit:{
            while(true)
            {
                clearScreen();
                System.out.println("Welcome to the register screen");

                
            }
        }
    }

    public String getUsername(Scanner scanner)
    {
        String username = "";

        while(true)
        {
            System.out.println("Enter a username: ");
            scanner.nextLine();

            if(username.equalsIgnoreCase("x"))
            {
                return "x";
            }

            if(!userservice.isValidUsername(username))
            {
                clearScreen();
                System.out.println("Username is not valid");
            }

            if(!userservice.isUniqueUsername(username))
            {
                clearScreen();
                System.out.println("Username is already taken. Please choose another one");
            }
            break;
        }

        return username;
       
    }

    public String getPassword()
    {
        String password = "";

        while(true)
        {
            
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
