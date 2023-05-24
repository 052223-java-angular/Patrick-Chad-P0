package com.YourStore.app.DAO;

import java.util.List;

public interface CrudDAO<T> {
   

    void update(T obj) ;

   
    void delete(T obj);


     List<T> findAll();

    


    


    
}
