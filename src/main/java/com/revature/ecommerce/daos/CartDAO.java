package com.revature.ecommerce.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import com.revature.ecommerce.models.Cart;

public class CartDAO implements CrudDAO<Cart>{

    @Override
    public void save(Cart obj) {
        
    }

    @Override
    public void update(Cart obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Cart obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<Cart> lookupUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lookupUser'");
    }

   
   
    
}
