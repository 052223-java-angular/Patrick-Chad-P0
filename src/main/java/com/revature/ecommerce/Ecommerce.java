package com.revature.ecommerce;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.ecommerce.services.RouterService;
import com.revature.ecommerce.utils.Session;

public class Ecommerce {
  private static final Logger logger = LogManager.getLogger(Ecommerce.class);
  
  public static void main(String args[]) {
    logger.info("------------------ START APPLICATION -----------------------------");
    Scanner scan = new Scanner(System.in);
    Session session = new Session();
    RouterService router = new RouterService();
    router.navigate("/home", scan, session);
    logger.info("------------------ END APPLICATION -----------------------------");
    scan.close();
  }

  

}
