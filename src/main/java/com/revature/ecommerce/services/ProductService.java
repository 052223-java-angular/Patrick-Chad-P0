package com.revature.ecommerce.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.revature.ecommerce.daos.CategoryDAO;
import com.revature.ecommerce.daos.ProductDAO;
import com.revature.ecommerce.models.Category;
import com.revature.ecommerce.models.Product;

public class ProductService {
    ProductDAO prodDAO = new ProductDAO();
    CategoryDAO catDAO = new CategoryDAO();
    

    /**
    *  Parameters: none
    *  Description: Connects to the ProductDao to get a list of all products
    *  Return: A list of products.
    *  Author: Chad Rotruck 
    */
    public List<Product> getAllProducts(){
        List<Product> listProducts = new ArrayList<Product>();
        listProducts = prodDAO.lookupProducts();
        return listProducts;
    }

    /**
    *  Parameters: name - String - name of product to use in query in ProductDao.
    *  Description: Connects with the ProductDao to retrieve a specific product based on product name.
    *  Return: A product object.
    *  Author: Chad Rotruck 
    */
    public Product getProductsByName(String name){
        Product product = new Product();
        
        Optional<Product> productOpt = prodDAO.lookupByProductName(name);

        if (productOpt.isPresent()){
            product = productOpt.get(); 
        }
                
        return product;
    }

    /**
    *  Parameters: none
    *  Description: returns a list of all categories
    *  Return: A list of categories
    *  Author: Chad Rotruck 
    */
    public List<Category> getAllCategories(){
        List<Category> cats = new ArrayList<Category>();

        cats = catDAO.getAllCategories();

        return cats;
    }

    /**
    *  Parameters: category - string - name of category to search for products
    *  Description: Gets a list of products based on category selected.
    *  Return: A list of products
    *  Author: Chad Rotruck 
    */
    public List<Product> getProductsByCategory(String category){
        List<Product> listProducts = new ArrayList<Product>();
        Product prod = new Product();

        List<Optional<Product>> productsOpt = prodDAO.lookupByCategory(category);
        if (productsOpt.isEmpty()){
            return null; 
        } else {
            int i = 0;
            for (Optional<Product> productOpt : productsOpt) {
                productOpt = productsOpt.get(i++);
                prod = productOpt.get();
                listProducts.add(prod);
            } 

            return listProducts;  
        }        
    }
    /**
    *  Parameters: min - double - minimum bound of price range.
                   max - double - maximum bound of price range.
    *  Description: gets a list of products within a specified price range.
    *  Return: A list of products.
    *  Author: Chad Rotruck 
    */
    // get products by price range
    public List<Product> getProductsByPriceRange(Double min, Double max){
        List<Product> listProducts = new ArrayList<Product>();
        Product prod = new Product();

        List<Optional<Product>> productsOpt = prodDAO.lookupByPriceRange(min, max);
        if (productsOpt.isEmpty()){
            return null; 
        } else {
            int i = 0;
            for (Optional<Product> productOpt : productsOpt) {
                productOpt = productsOpt.get(i++);
                prod = productOpt.get();
                listProducts.add(prod);
            } 

            return listProducts;  
        }   

    }

    /**
    *  Parameters: user_id - string - The user id used in the search for products.
    *  Description: Gets a list of products that the user as purchased.
    *  Return: A list of products.
    *  Author: Chad Rotruck 
    */
    public List<Product> getListOfProductsPurchased(String user_id)
    {
        List<Optional<Product>> productsOpt = new LinkedList<Optional<Product>>();
        List<Product> listProducts = new LinkedList<Product>();
        Product prod = new Product();
        productsOpt = prodDAO.getListOfProductsPurchased(user_id);
        if (productsOpt.isEmpty()){
            return null; 
        } else {
            int i = 0;
            for (Optional<Product> productOpt : productsOpt) {
                productOpt = productsOpt.get(i++);
                prod = productOpt.get();
                listProducts.add(prod);
            } 

            return listProducts;  
        }   
    }
    
}
