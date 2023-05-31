# P0 - Pair Programming eCommerce Project

## Introduction

This is a Java-based command-line interface (CLI) eCommerce application. The application will be primarily built using Java and will utilize a PostgreSQL database to store product and user information.

## How to start application

To Start the application, first open a command prompt and cd to the directory that the houses the Ecommerence.java file. then type: - java Ecommerence.java

## Instructions for moving around screens

- Follow on screen prompts.
- You will land at the home page first. This will give you two options. You can either log in or register if you do not have an account.
  - If selecting user login, you will be prompted for username and password.
  - If you are registering, you will be prompted for your username. The program will then check to see if the user exists. If it does not then you will be prompted for password creation and password verification. It will then save your credentials then prompt you to log in.
- After logging in, you will come to a user main menu where you will be prompted to enter a number for choice or x to exit program.
  - If user selects [1] then this will take user to a products page with options to select how they want to view products.
    - In the product screen you will have a menu that you will be prompted to enter an number for choice or x to exit program.
      - if user selects [1] then it will take them to the products list
        - A list of products will be shown. Enter the number of which product to select.
          - A details page of the product will be shown and you will be asked if you want to add to cart.
            - [y] will add item to your cart.
            - [n] will return give you a message stating that item was not added to cart. Press enter to continue and will be product page.
      - if user selects [2] then it will take them to the product selection entry.
      - if user selects [3] then it will take them to the product category list.
      - if user selects [4] then it will take them to the product price range entry.
      - if user selects [5] then it will take them to thier cart.
      - if user selects [x] the it will take them to the previous menu
  - If user selects [2] then this will take user to thier cart screen.
  - if user selects [3] then this will take user to thier past orders screen.
  - If user selects [4] then this will take user to the reviews screen.
  - If user selects [x] then this will exit program.
