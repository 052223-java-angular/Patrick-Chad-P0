package com.revature.ecommerce.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.ecommerce.daos.CartDAO;
import com.revature.ecommerce.models.Category;
import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.services.CartService;
import com.revature.ecommerce.services.ProductService;
import com.revature.ecommerce.services.RouterService;

import lombok.AllArgsConstructor;

/**
 * The ProductScreen class represents the products screen of the eCommerence Application.
 * It implements the IScreen interface.
 */
@AllArgsConstructor
public class ProductScreen implements IScreen {
    private final RouterService router;
    private final ProductService prodServ;
    private static final Logger logger = LogManager.getLogger(ProductScreen.class);
    
    @Override
    public void start(Scanner scan) {
        ProductDetailsScreen detailScreen = new ProductDetailsScreen();
        CartService cartservice = CartService.callCartServiceConstructor(new CartDAO());
        String input = "";
        logger.info("Navigated to Products screen.");
        exit:{
            while(true){
                // print menu
                clearScreen();
                System.out.println("Welcome to YourStore where we have everything you don't want!");
                System.out.println("\n [1] Browse Products");
                System.out.println(" [2] Search products by name");
                System.out.println(" [3] Search products by category");
                System.out.println(" [4] Search products by price range");
                System.out.println("[5] View Cart");
                System.out.println(" [x] Exit: ");
        
                System.out.print("\nEnter: ");
                input = scan.nextLine(); // get user input

                if (input.equalsIgnoreCase("x")) {
                    // exit menu
                    break exit;
                } 
        
                switch (input) {
                    case "1":
                        // browse products
                        logger.info("User selected browse products.");
                        List<Product> products = new ArrayList<Product>();
                        products = prodServ.getAllProducts();

                        menuExit:{
                            while(true){                                
                                //display items
                                displayList(products, scan);

                                //let user select which item to view
                                System.out.print("\n Select which product to view. Enter x to exit: ");
                                input = scan.nextLine();

                                // if user wishes to exit
                                if (input.equalsIgnoreCase("x")) {
                                    router.navigate("/products", scan);
                                }

                                //validate numeric entry
                                if(isNumeric(input) && Integer.parseInt(input) <= products.size()){
                                    // valid entry
                                    Product selectedProd = new Product();
                                    selectedProd = products.get(Integer.parseInt(input) - 1);

                                    detailScreen.display(scan, selectedProd); 
                                    break menuExit;
                                } else {
                                    // invalid entry
                                    System.out.println("Invalid Entry. Press Enter to continue...");
                                    scan.nextLine();
                                }
                            }
                        }
                        
                        break;
                    case "2":
                        productNameExit:{
                            while(true){
                                clearScreen();
                                // search products by name
                                logger.info("User selected search by product name.");
                                System.out.print("Enter name of product [enter x to exit]: ");
                                input = scan.nextLine();

                                // if user wishes to exit
                                if (input.equalsIgnoreCase("x")) {
                                    router.navigate("/products", scan);
                                    break productNameExit;
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
                                    addtocart:{
                                        while(true){
                                            // item found
                                            System.out.println("\n --- Product Listing ---");
                                            System.out.println("Product Name: " + prod.getName());
                                            System.out.println("Product Description: " + prod.getDescription());
                                            System.out.println("Product Price: $" + prod.getPrice());
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
                                                    cartservice.addToCart(prod, qty);
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
                                // if user wishes to exit
                                if (input.equalsIgnoreCase("x")) {
                                    router.navigate("/products", scan);
                                }                
                            }
                        }      
                        break;
                    case "3":
                        // search products by category
                        logger.info("User selected search products by category.");
                        categoryExit:{   
                            while (true){
                                // if user wishes to exit
                                if (input.equalsIgnoreCase("x")) {
                                    //router.navigate("/products", scan);
                                    break categoryExit;
                                }

                                // search database and return all categories
                                List<Category> cats = new ArrayList<Category>();
                                cats = prodServ.getAllCategories();

                                // display categories
                                clearScreen();
                                System.out.println("----------- Categories -----------");
                                int index = 0;
                                for (Category cat : cats) {
                                    System.out.println("[" + ++index + "] " + cat.getName());
                                }

                                //let user select which item to view
                                System.out.print("\n Select which category to view. Enter x to exit: ");
                                input = scan.nextLine();

                                // accept user choice
                                // if user wishes to exit
                                if (input.equalsIgnoreCase("x")) {
                                    router.navigate("/products", scan);
                                }

                                //validate numeric entry
                                if(isNumeric(input) && Integer.parseInt(input) <= cats.size()){
                                    // valid entry
                                    // search database for items in that category
                                    Category cat = new Category();
                                    cat = cats.get(Integer.parseInt(input) - 1);
                                    
                                    List<Product> catProducts = new ArrayList<Product>();
                                    catProducts = prodServ.getProductsByCategory(cat.getId());
                                    System.out.println(catProducts);
                                    menuExit:{
                                        while(true){
                                            //loop through products and output each product
                                            //int catIndex = 0;
                                            clearScreen();
                                            System.out.println("----------- Products -----------");

                                            //display items
                                            displayList(catProducts, scan);
                                            
                                            // for (Product product : catProducts) {
                                            //     System.out.println("[" + ++catIndex + "] " + product.getName());
                                            // }
            
                                            //let user select which item to view
                                            System.out.print("\n Select which product to view. Enter x to exit: ");
                                            input = scan.nextLine();
            
                                            // if user wishes to exit
                                            if (input.equalsIgnoreCase("x")) {
                                                router.navigate("/products", scan);
                                            }
            
                                            //validate numeric entry
                                            if(isNumeric(input) && Integer.parseInt(input) <= catProducts.size()){
                                                // valid entry
                                                Product selectedProd = new Product();
                                                selectedProd = catProducts.get(Integer.parseInt(input) - 1);
            
                                                detailScreen.display(scan, selectedProd); 
                                                break menuExit;
                                            } else {
                                                // invalid entry
                                                System.out.println("Invalid Entry. Press Enter to continue...");
                                                scan.nextLine();
                                            }
                                        }
                                    }
                                    break categoryExit;
                                } else {
                                    // invalid entry
                                    System.out.println("Invalid Entry. Press Enter to continue...");
                                    scan.nextLine();
                                }
                                
                                // TODO: Add to cart
                                
                            }
                        }
                        break;
                    case "4":
                        
                        // search products by price range
                        priceExit:{
                            while (true){
                                // if user wishes to exit
                                if (input.equalsIgnoreCase("x")) {
                                    router.navigate("/products", scan);
                                }

                                // display price range screen
                                clearScreen();
                                System.out.println("------ Price Range ------");

                                // get min amt from user
                                System.out.print("Please enter minimum: ");
                                Double min = 0.0;
                                input = scan.nextLine();

                                if(isNumeric(input) && Double.parseDouble(input) >= 0.0){
                                    min = Double.parseDouble(input);
                                }

                                //get max amt from user
                                System.out.print("Please enter maximum: ");
                                Double max = 0.0;
                                input = scan.nextLine();

                                if(isNumeric(input) && Double.parseDouble(input) >= 0.0){
                                    max = Double.parseDouble(input);
                                }

                                // get list of products from database
                                List<Product> priceProds = new ArrayList<Product>();
                                priceProds = prodServ.getProductsByPriceRange(min, max);

                                //display items
                                displayList(priceProds, scan);

                                //let user select which item to view
                                System.out.print("\n Select which product to view. Enter x to exit: ");
                                input = scan.nextLine();

                                // if user wishes to exit
                                if (input.equalsIgnoreCase("x")) {
                                    router.navigate("/products", scan);
                                }

                                //validate numeric entry
                                if(isNumeric(input) && Integer.parseInt(input) <= priceProds.size()){
                                    // valid entry
                                    Product selectedProd = new Product();
                                    selectedProd = priceProds.get(Integer.parseInt(input) - 1);

                                    detailScreen.display(scan, selectedProd); 
                                    break priceExit;
                                } else {
                                    // invalid entry
                                    System.out.println("Invalid Entry. Press Enter to continue...");
                                    scan.nextLine();
                                }

                            }
                        }

                        
                    default:
                        // invalid input
                        clearScreen();  
                        System.out.println("Option mush be 1,2,3,4 or x! Press enter to continue");
                        scan.nextLine();
                        break;

                    case "5":
                        
                }
            }
        }
        router.navigate("/home", scan);
    }

    /* ---------- Methods ------------- */

    /**
     *  Parameters: prods - List<Product> - list of products to display
     *              scan - Scanner - used to capture input from user
     *  Description: Displays the list to the console.
     *  Return: none
     */
    private void displayList(List<Product> prods, Scanner scan){
        //loop through products and output each product
        int catIndex = 0;
        clearScreen();
        System.out.println("----------- Products -----------");
        
        for (Product product : prods) {
            System.out.println("[" + ++catIndex + "] " + String.format("%-20s",product.getName()) + "     Price: $" + String.format("%1$.2f",product.getPrice()) + "     Available: " + product.getQty_on_hand());
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
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }    
}
