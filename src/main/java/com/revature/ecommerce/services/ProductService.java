package com.revature.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.ecommerce.daos.ProductsDAO;
import com.revature.ecommerce.models.Product;

public class ProductService {
    ProductsDAO prodDAO = new ProductsDAO();
    
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

    // get products by category
    public List<Product> getProductsByCategory(String category){
        List<Product> listProducts = new ArrayList<Product>();

        listProducts = prodDAO.lookupByCategory(category);

        return null;
    }

    // get products by price range
    
}
