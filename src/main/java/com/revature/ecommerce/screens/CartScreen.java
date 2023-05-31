package com.revature.ecommerce.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.services.CartService;
import com.revature.ecommerce.services.RouterService;
import com.revature.ecommerce.utils.Session;

public class CartScreen implements IScreen{
   // private static CartDAO cartdao;
    @Override
    public void start(Scanner scan) {
        
        
       
    }

    public void start(Scanner scanner, Session session)
    {
        
        
        CartService cartService = CartService.getInstance();
        

        List<Product> items = CartService.callGetCartItems(session.getCart_id());
       //ArrayList<Product> items = CartService.callGetItemsFromCart(session.getCart_id());
        
        if(items != null)
        {
            String input = "";
            for(int count = 0; count < items.size(); count++)
            {
                System.out.println(items.get(count));
            }
        
            while((input.equals("1")) || (input.equals("2") || input.equals("")))
            {          
               
                System.out.println("[1] Delete items");
                System.out.println("[2] Increase or decrease quantity of items");
                System.out.println("[3] Back to the home page");
                input = scanner.nextLine();
                
                    switch(input)
                    {
                        case "1":
                            System.out.println("Type in the name of the item that you would like to delete:");
                            input = scanner.nextLine();
                            for(int count = 0; count < items.size(); count++)
                            {
                                if(items.get(count).getName().equals(input))
                                {
                                    System.out.println("Are you sure you want to delete " + items.get(count).getName() + "?(y/n)");
                                    input = scanner.nextLine();
                                    if(input.equalsIgnoreCase("y"))
                                    {
                                        CartService.deleteFromCart(items.get(count).getName());
                                        break;
                                    }
                                    else
                                    {
                                        continue;
                                    }
                                }
                            }

                        break;
                        case "2":
                        String name = "";
                        System.out.println("Enter the name of the item whose quantity you would like to modify:");
                        name = scanner.nextLine();
                        
                        for(int count = 0; count < items.size(); count++)
                        {
                            if(items.get(count).getName().equals(name))
                            {
                                System.out.println("Are you sure you want to change the quantity of " + items.get(count).getName() + "?(y/n)");
                                input = scanner.nextLine();
                                if(input.equalsIgnoreCase("y"))
                                {
                                    System.out.println("Enter the quantity you want:");
                                    input = scanner.nextLine();
                                    try{
                                        int quantity = Integer.parseInt(input);
                                        if(quantity == 0)
                                        {
                                            CartService.deleteFromCart(name);
                                        }
                                        CartService.callUpdateQuantity(items.get(count).getName(), quantity);
                                    }
                                    catch(NumberFormatException ex)
                                    {
                                        System.out.println("Those were not numbers. Press Enter to Continue. . .");
                                        scanner.nextLine();
                                        continue;
                                    }
                                    
                                }
                                else
                                {
                                    System.out.println("Did not modify item. Press enter to continue . . .");
                                    continue;
                                }
                            }
                        }
                        
                        
                        break;
                    }
            
                    
                      
            
        }

        new RouterService().navigate("/user", scanner, session);
       }
        else
        {
            System.out.println("Your cart has not items in it. Press Enter to go back to the home page . . .");
            scanner.nextLine();
            new RouterService().navigate("/user", scanner, session);
        }
    }


        


        

        
        
    


    
}
