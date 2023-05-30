package com.revature.ecommerce.screens;

import java.util.Scanner;

import com.revature.ecommerce.services.RouterService;
import com.revature.ecommerce.utils.Session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class HomeScreen implements IScreen {
    private final RouterService router;

    @Override
    public void start(Scanner scan) {
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    
    public void start(Scanner scan, Session session) {
        String input = "";

        clearScreen();
        System.out.println("Welcome to YourStore where we have everything you don't want!");
        
        System.out.println(" [1] Login");
        System.out.println(" [2] Register");

        System.out.print("\nEnter: ");
        input = scan.nextLine();

        switch(input.toLowerCase()){
            case "1":
                LoginScreen.getInstance().start(scan, session);
                //router.navigate("/user", scan);
                break;
            case "2": 
                RegisterScreen.getInstance().start(scan, session);
                //router.navigate("/user", scan);
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
