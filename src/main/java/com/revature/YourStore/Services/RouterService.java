package com.revature.YourStore.Services;

import java.util.Scanner;

import com.revature.YourStore.Screens.HomeScreen;

public class RouterService {

    public void navigate(String path, Scanner scan){
        switch(path){
            case "/home":
                new HomeScreen(this).start(scan);
                break;
            case "/products":
                break;
            default:
                break;
        }
    }
}
