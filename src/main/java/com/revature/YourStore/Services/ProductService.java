package com.revature.YourStore.Services;

import java.util.Optional;

import com.revature.YourStore.DAOs.ProductsDAO;
import com.revature.YourStore.Models.Product;

public class ProductService {
    ProductsDAO prodDAO = new ProductsDAO();

    // get products by name
    public Product getProductsByName(String name){
        Product product = new Product();
        
        Optional<Product> productOpt = prodDAO.lookupByProductName(name);

        product = productOpt.get();
        
        return product;
    }

    // get products by category

    // get products by price range
    
}
