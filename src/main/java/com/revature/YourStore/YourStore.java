package com.revature.YourStore;

import java.util.Scanner;

import com.revature.YourStore.Services.RouterService;

public class YourStore {
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);
    RouterService router = new RouterService();
    router.navigate("/home", scan);
  }
}
