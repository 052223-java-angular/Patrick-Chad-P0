package com.revature.YourStore.Screens;

import java.util.Scanner;

import com.revature.YourStore.Services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HomeScreen implements IScreen {
    private final RouterService router;

    @Override
    public void start(Scanner scan) {
        String input = "";

        clearScreen();
        System.out.println("Welcome to YourStore where we have everything you don't want!");
        System.out.println("\n [1] Products");

        System.out.print("\nEnter: ");
        input = scan.nextLine();

        switch(input.toLowerCase()){
            case "1":
                router.navigate("/products", scan);
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
