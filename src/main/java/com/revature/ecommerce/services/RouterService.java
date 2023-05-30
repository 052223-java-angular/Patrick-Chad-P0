package com.revature.ecommerce.services;

import com.revature.ecommerce.screens.*;
import com.revature.ecommerce.utils.Session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Scanner;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
                rs.start(scan,session);
                break;   
            case "/user":
                new UserScreen(this).start(scan,session);
                break;
            case "/writereview":
                new ReviewScreen(prodServ, this).start(scan,session);
            //    RegisterScreen rs = RegisterScreen.getInstance();
            //    rs.start(scan, session);
                break;
            case "/carts":
                new CartScreen().start(scan, session);
                break;
            default:
                break;
        }
    }
}
