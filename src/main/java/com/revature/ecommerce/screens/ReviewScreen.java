package com.revature.ecommerce.screens;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.models.Review;
import com.revature.ecommerce.models.User;
import com.revature.ecommerce.services.ProductService;
import com.revature.ecommerce.services.ReviewService;
import com.revature.ecommerce.services.RouterService;
import com.revature.ecommerce.utils.Session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewScreen implements IScreen{
    private ProductService productService = new ProductService();
    private RouterService router;
    private static final Logger logger = LogManager.getLogger(ProductScreen.class);
    private static Review review = new Review();
    private static ReviewService reviewService = new ReviewService();
    private static Session session;

    /**
     *  Parameters: scan - Scanner - used to get input from user.
     *  Description: Displays the menu for Reviews
     *  Return: none
     */
    @Override
    public void start(Scanner scan){

    }

    public void start(Scanner scan, Session session) {
        String input = "";
        //pull list of products purchased
        List<Product> products = new LinkedList<Product>();
        products = productService.getListOfProductsPurchased(session.getId());
        exit:{
            while(true){
                //display menu
                clearScreen();
                System.out.println("\n ----- Previous Purchases -----");

                //display list of products
                int index = 0;
                for (Product product : products) {
                    System.out.println("[" + ++index + "] " + String.format("%-20s",product.getName()));
                }
                System.out.println(" [x] Exit");

                System.out.print("\nPlease select which product to review. ");
                input = scan.nextLine();

                // if user wishes to exit
                if (input.equalsIgnoreCase("x")) {
                    router.navigate("/products", scan, session);
                }

                //validate numeric entry
                if(isNumeric(input) && Integer.parseInt(input) <= products.size()){
                    // valid entry
                    Product selectedProd = new Product();
                    selectedProd = products.get(Integer.parseInt(input) - 1);

                    //set product id
                    review.setProduct_id(selectedProd.getId());

                    //set user id
                    review.setUser_id(session.getId());

                    System.out.print("Please enter rating: ");
                    input = scan.nextLine();
                    review.setRating(Integer.parseInt(input));

                    System.out.print("Please enter your comment: ");
                    input = scan.nextLine();
                    review.setComment(input);

                    //save review
                    reviewService.save(review);
                    break exit;
                } else {
                    // invalid entry
                    System.out.println("Invalid Entry. Press Enter to continue...");
                    scan.nextLine();
                }
            }
        }
    }

    /* -------- Helper Methods -------- */

    /**
     *  Parameters: none
     *  Description: Clears the console screen.
     *  Return: none
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     *  Parameters: strNum - String - input that will be verified as numeric.
     *  Description : Checks to see if the string is numeric
     *  Return: Returns true if numeric else false.
     */
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
            logger.info("IsNumeric StrNum: " + i);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }    
}
