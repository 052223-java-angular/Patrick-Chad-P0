package com.revature.ecommerce.services;

import java.util.Scanner;

import com.revature.ecommerce.daos.RoleDAO;
import com.revature.ecommerce.daos.UserDAO;
import com.revature.ecommerce.screens.HomeScreen;
import com.revature.ecommerce.screens.LoginScreen;
import com.revature.ecommerce.screens.ProductScreen;
import com.revature.ecommerce.screens.RegisterScreen;

public class RouterService {
    ProductService prodServ = new ProductService();
    
    public void navigate(String path, Scanner scan){
        switch(path){
            case "/home":
                new HomeScreen(this).start(scan);
                break;
            case "/products":
                new ProductScreen(this, prodServ).start(scan);
                break;
            case "/login":
                LoginScreen.getInstance();
                break;
            case "/register":
               RegisterScreen.getInstance();
               break;
            default:
                break;
        }
    }
}
