package com.revature.ecommerce;

import java.util.Scanner;

import com.revature.ecommerce.services.RouterService;

public class Ecommerce {
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);
    RouterService router = new RouterService();
    router.navigate("/home", scan);
  }
}
