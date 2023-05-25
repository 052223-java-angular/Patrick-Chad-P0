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
                // print menu
                clearScreen();
                System.out.println("Welcome to YourStore where we have everything you don't want!");
                System.out.println("\n [1] Search products by name");
                System.out.println(" [2] Search products by category");
                System.out.println(" [3] Search products by price range");
                System.out.println(" [x] Exit: ");
        
                System.out.print("\nEnter: ");
                input = scan.nextLine(); // get user input

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
                        if (prod.getId() == null){
                            // no item found
                            System.out.println("No product found. Press enter to continue...");
                            scan.nextLine();
                        } else {
                            // item found
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
                        break;
                    case "2":
                        // search products by category
                        break;
                    case "3":
                        // search products by price range
                        break;
                
                    default:
                        clearScreen();  
                        System.out.println("Option mush be 1,2,3 or x! Press enter to continue");
                        scan.nextLine();
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
