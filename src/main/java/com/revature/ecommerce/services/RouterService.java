package com.revature.ecommerce.services;

import java.util.Scanner;


import com.revature.ecommerce.screens.HomeScreen;
import com.revature.ecommerce.screens.LoginScreen;
import com.revature.ecommerce.screens.ProductScreen;
import com.revature.ecommerce.screens.RegisterScreen;

public class RouterService {
    ProductService prodServ = new ProductService();

    
    
    public void navigate(String path, Scanner scan){
        System.out.println("In routerService.navigate()");
        switch(path){
            case "/home":
                new HomeScreen(this).start(scan);
                break;
            case "/products":
                new ProductScreen(this, prodServ).start(scan);
                break;
            case "/login":
                LoginScreen login = LoginScreen.getInstance();
                login.start(scan);
                break;
            case "/register":
               RegisterScreen rs = RegisterScreen.getInstance();
               rs.start(scan);
               break;
            default:
                break;
        }
    }
}
