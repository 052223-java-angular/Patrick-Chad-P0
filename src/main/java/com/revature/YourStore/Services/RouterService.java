package com.revature.YourStore.Services;

import java.util.Scanner;

import com.revature.YourStore.Screens.HomeScreen;
import com.revature.YourStore.Screens.ProductScreen;

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
            default:
                break;
        }
    }
}
