package com.revature.ecommerce.screens;

import java.util.Scanner;

import com.revature.ecommerce.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HomeScreen implements IScreen {
    private final RouterService router;

    @Override
    public void start(Scanner scan) {
        String input = "";

        clearScreen();
        System.out.println("Welcome to YourStore where we have everything you don't want!");
        
        System.out.println(" [1] Login");
        System.out.println(" [2] Register");
        //System.out.println(" [3] Products");

        System.out.print("\nEnter: ");
        input = scan.nextLine();

        switch(input.toLowerCase()){
            case "1":
                router.navigate("/products", scan);
                break;
            case "2":
                LoginScreen.getInstance().start(scan);
            case "3": 
                RegisterScreen.getInstance().start(scan);
            default:
                break;
        }
    }
    
    /* -------- Helper Methods -------- */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
