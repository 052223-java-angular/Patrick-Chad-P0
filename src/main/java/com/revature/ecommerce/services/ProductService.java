package com.revature.ecommerce.services;

import java.util.ArrayList;
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

        //Optional<Product> productOpt = prodDAO.lookupByCategory(category);
        //if (productOpt.isPresent()){
            //for (Product product : listProducts) {
                
            //}
            //listProducts = productOpt.get(); 
        //}

        return null;
    }

    // get products by price range
    
}
