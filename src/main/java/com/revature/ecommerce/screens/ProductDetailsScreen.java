package com.revature.ecommerce.screens;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.models.Review;
import com.revature.ecommerce.services.ReviewService;
import com.revature.ecommerce.services.CartService;
import com.revature.ecommerce.utils.Session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductDetailsScreen implements IScreen {
    Scanner scan;
    //private final RouterService router;
    //private final ProductService prodServ;
    private static final CartService cartservice = CartService.getInstance();
    private static final Logger logger = LogManager.getLogger(ProductScreen.class);

    public void display(Scanner scan, Product prod, Session session){
        
        String input = "";
        //clearScreen();
        logger.info("Navigated to Product Details Page.");
        System.out.println("\n --- Product Listing ---");
        System.out.println("Product Name: " + prod.getName());
        System.out.println("Product Description: " + prod.getDescription());
        System.out.println("Product Price: $" + String.format("%1$.2f",prod.getPrice()));
        //displayReviews(prod);

        addtocart:{
            while(true){
                System.out.print("\nWould you like to add to your cart? (y/n): ");
                input = scan.nextLine();
                switch (input){
                    case "y":
                        // add to cart  
                        int qty = 0;                                  
                        System.out.print("Please enter desired quantity: ");
                        input = scan.nextLine(); 
                        qty = Integer.parseInt(input);

                        // TODO: send item to cart items
                        cartservice.addToCart(prod, qty, session);
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
                break;
            }
        }
        
    }

    @Override
    public void start(Scanner scan) {
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    } 

    /* -------- Methods -------- */

    /**
     *  Parameters: prods - List<Product> - list of products to display          
     *  Description: Displays the list of reviews to the console.
     *  Return: none
     */
    /*private void displayReviews(Product prod){
        System.out.println("\n-------- Product Reviews --------");
        //get reviews from database
        ReviewService reviewService = new ReviewService();
        List<Review> reviews = new LinkedList<Review>();

        reviews = reviewService.getAllReviews(prod);

        //loop through list and display each review
        int index = 0;
        for (Review review : reviews) {
            System.out.println(String.format("%-5s","[" + ++index + "]") + String.format("%-55s", "UserID:  " + review.getUser_id() ) + String.format("%-30s", "Rating:  " + review.getRating() + "/5 stars") + String.format("%-5s", "Comment:  " + review.getComment()));
        }
    }*/
    
    /* -------- Helper Methods -------- */

    /**
     *  Parameters: none
     *  Description: Clears the console.
     *  Return: none
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
    

