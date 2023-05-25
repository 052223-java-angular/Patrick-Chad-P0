package com.revature.ecommerce.daos;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> {
    void save(T obj);

    void update(T obj) ;

    void delete(T obj);
    
    void lookupUser(String username, String password);

    List<T> lookupProducts();

    Optional<T> lookupByProductName(String name);

}
