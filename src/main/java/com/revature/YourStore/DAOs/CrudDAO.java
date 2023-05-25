package com.revature.YourStore.DAOs;

import java.util.List;
import java.util.Optional;

import com.revature.YourStore.Models.Product;

public interface CrudDAO<T> {
   

    void update(T obj) ;

   
    void lookupUser(String username, String password);

    List<T> lookupProducts();

    Optional<Product> lookupByProductName(String name);


    


    
}
