package com.revature.ecommerce.daos;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> {
    void save(T obj);

    void update(T obj) ;

    void delete(T obj);
    
    Optional<T> lookupUser(String username, String password);



}
