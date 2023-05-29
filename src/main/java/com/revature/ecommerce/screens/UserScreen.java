package com.revature.ecommerce.screens;

import java.util.Scanner;

import com.revature.ecommerce.services.CartService;
import com.revature.ecommerce.services.RouterService;
import com.revature.ecommerce.utils.Session;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class UserScreen implements IScreen {
    private final RouterService router;
    //private final Session session;

    @Override
    public void start(Scanner scan) {
        String input = "";

        clearScreen();
        System.out.println("Welcome to YourStore where we have everything you don't need!");
        
        System.out.println(" [1] View Products");
        System.out.println(" [2] View Cart");
        System.out.println(" [3] Orders");
        System.out.println(" [4] Leave review");
        System.out.println(" [x] Exit");

        System.out.print("\nEnter: ");
        input = scan.nextLine();

        switch(input.toLowerCase()){
            case "1":
                router.navigate("/products", scan);
                break;
            case "2":
                LoginScreen.getInstance().start(scan);
                break;
            case "3": 
                RegisterScreen.getInstance().start(scan);
                break;
            case "4":
                router.navigate("/reviews",scan);
                break;
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
