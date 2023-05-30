package com.revature.ecommerce.services;

import com.revature.ecommerce.screens.*;
import com.revature.ecommerce.utils.Session;

import java.util.Scanner;

public class RouterService {
    ProductService prodServ = new ProductService();
    
    public void navigate(String path, Scanner scan, Session session){
        System.out.println("In routerService.navigate()");
        switch(path){
            case "/home":
                new HomeScreen(this).start(scan, session);
                break;
            case "/products":
                new ProductScreen(this, prodServ).start(scan, session);
                break;
            case "/login":
                LoginScreen login = LoginScreen.getInstance();
                login.start(scan, session);
                break;
            case "/register":
               RegisterScreen rs = RegisterScreen.getInstance();
               rs.start(scan, session);
               break;
            case "/user":
                new UserScreen(this).start(scan, session);
               break;
            case "/carts":
                new CartScreen().start(scan, session);
                break;
            default:
                break;
        }
    }
}
