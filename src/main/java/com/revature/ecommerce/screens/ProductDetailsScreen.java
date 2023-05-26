package com.revature.ecommerce.screens;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.ecommerce.models.Product;
//import com.revature.ecommerce.services.ProductService;
//import com.revature.ecommerce.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductDetailsScreen implements IScreen {
    //private final RouterService router;
    //private final ProductService prodServ;
    private static final Logger logger = LogManager.getLogger(ProductScreen.class);
    
    public void display(Scanner scan, Product prod){
        String input = "";
        clearScreen();
        logger.info("Navigated to Product Details Page.");
        System.out.println("\n --- Product Listing ---");
        System.out.println("Product Name: " + prod.getName());
        System.out.println("Product Description: " + prod.getDescription());
        System.out.println("Product Price: $" + prod.getPrice());
        System.out.print("\nWould you like to add to your cart? (y/n): ");
        input = scan.nextLine();

        addtocart:{
            while(true){
                switch (input){
                    case "y":
                        // add to cart  
                        int qty = 0;                                  
                        System.out.print("Please enter desired quantity: ");
                        input = scan.nextLine(); 
                        qty = Integer.parseInt(input);

                        // TODO: send item to cart items
                        System.out.println("added " + qty + " to your cart. Press enter to continue....");
                        scan.nextLine();                       
                    
                        break addtocart;
                    case "n":
                        // reload menu
                        System.out.println("Item not added to cart. Press enter to continue....");
                        scan.nextLine();

                    break addtocart;
                    default:
                        System.out.println("Invalid entry. Must select (y/n). Press enter to continue....");
                        scan.nextLine();
                    break;
                }
            }
        }
        
    }

    @Override
    public void start(Scanner scan) {
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    } 
    
        /* -------- Helper Methods -------- */
        private void clearScreen() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
}
    

