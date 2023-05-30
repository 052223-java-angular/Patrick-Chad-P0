package com.revature.ecommerce.screens;

import java.util.List;
import java.util.Scanner;



import com.revature.ecommerce.daos.CartDAO;
import com.revature.ecommerce.daos.OrderDAO;
import com.revature.ecommerce.daos.ProductDAO;
import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.services.CartService;
import com.revature.ecommerce.services.RouterService;

public class CartScreen implements IScreen{
   // private static CartDAO cartdao;
    @Override
    public void start(Scanner scan) {
        String input = "";
        Scanner sc = new Scanner(System.in);
        CartService cartService = CartService.getInstance();
        

        List<Product> items = CartService.callGetCartItems(cartService.getCartId());
        if(items != null)
        {
                         
                for(int count = 0; count < items.size() - 1; count++)
                {
                    System.out.println(items.get(count));
                }
            
                System.out.println("[1] Delete items");
                System.out.println("[2] Increase or decrease quantity of items");
                System.out.println("[3] Back to the home page");
                input = sc.nextLine();
            
                    switch(input)
                    {
                        case "1":
                            System.out.println("Type in the name of the item that you would like to delete:");
                            sc.nextLine();
                            for(int count = 0; count < items.size(); count++)
                            {
                                if(items.get(count).getName().equals(input))
                                {
                                    System.out.println("Are you sure you want to delete " + items.get(count).getName() + "?(y/n)");
                                    input = sc.nextLine();
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
                        System.out.println("Enter the name of the item whose quantity you would like to modify:");
                        input = sc.nextLine();
                        
                        for(int count = 0; count < items.size(); count++)
                        {
                            if(items.get(count).getName().equals(input))
                            {
                                System.out.println("Are you sure you want to change the quantity of " + items.get(count).getName() + "?(y/n)");
                                input = sc.nextLine();
                                if(input.equalsIgnoreCase("y"))
                                {
                                    System.out.println("Enter the quantity you want:");
                                    input = sc.nextLine();
                                    try{
                                        int quantity = Integer.parseInt(input);
                                        CartService.callUpdateQuantity(items.get(count).getName(), quantity);
                                    }
                                    catch(NumberFormatException ex)
                                    {
                                        System.out.println("Those were not numbers. Press Enter to Continue. . .");
                                        sc.nextLine();
                                        continue;
                                    }
                                    
                                }
                            }
                        }
                        sc.close();
                        
                        break;
                    
                    
            
           }
        }
        else
        {
            System.out.println("Your cart has not items in it. Press Enter to go back to the home page . . .");
            scan.nextLine();
            new RouterService().navigate("/user", scan);
        }
        
       
    }


        


        

        
        
    


    
}
