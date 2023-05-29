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
    
    // browse products
    public List<Product> getAllProducts(){
        List<Product> listProducts = new ArrayList<Product>();
        listProducts = prodDAO.lookupProducts();
        return listProducts;
    }

    // get products by name
    public Product getProductsByName(String name){
        Product product = new Product();
        
        Optional<Product> productOpt = prodDAO.lookupByProductName(name);

        if (productOpt.isPresent()){
            product = productOpt.get(); 
        }
                
        return product;
    }

    // get all categories
    public List<Category> getAllCategories(){
        List<Category> cats = new ArrayList<Category>();

        cats = catDAO.getAllCategories();

        return cats;
    }

    // get products by category
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
