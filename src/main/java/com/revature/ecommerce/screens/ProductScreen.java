package com.revature.ecommerce.screens;

import java.util.Scanner;

import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.services.ProductService;
import com.revature.ecommerce.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductScreen implements IScreen {
    private final RouterService router;
    private final ProductService prodServ;
    
    @Override
    public void start(Scanner scan) {
        String input = "";
        exit:{
            while(true){
                clearScreen();
                System.out.println("Welcome to YourStore where we have everything you don't want!");
                System.out.println("\n [1] Search products by name");
                System.out.println(" [2] Search products by category");
                System.out.println(" [3] Search products by price range");
                System.out.println(" [x] Exit: ");
        
                System.out.print("\nEnter: ");
                input = scan.nextLine();

                if (input == "x") {
                    // exit menu
                    break exit;
                }
        
                switch (input) {
                    case "1":
                        clearScreen();
                        // search products by name
                        System.out.print("Enter name of product [enter x to exit]: ");
                        input = scan.nextLine();

                        // if user wishes to exit
                        if (input.equalsIgnoreCase("x")) {
                            router.navigate("/products", scan);
                        }

                        // if user enters product name
                        Product prod = new Product();
                        prod = prodServ.getProductsByName(input);
                        clearScreen();
                        System.out.println("\n --- Product Listing ---");
                        System.out.println("Pruduct Name: " + prod.getName());
                        break;
                    case "2":
                        // search products by category
                        break;
                    case "3":
                        // search products by price range
                        break;
                
                    default:
                        break;
                }
            }
        }
    }

    /* -------- Helper Methods -------- */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}
