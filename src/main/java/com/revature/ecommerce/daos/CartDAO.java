package com.revature.ecommerce.daos;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.revature.ecommerce.models.Cart;
import com.revature.ecommerce.utils.ConnectionFactory;
import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.services.CartService;
import com.revature.ecommerce.utils.ConnectionFactory;

public class CartDAO implements CrudDAO<Cart>
{

    @Override
    public void save(Cart obj) {
        //TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented Method 'save'");
    }

    @Override
    public void update(Cart cart) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented Method 'update'");
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

    /*
    *   Parameters: name - String - This is the name of the product to search for.

        Purpose: this routine is used to query the database to get a specific item by name.

        Return: This routine will return an Optional<Product>.
    */
    public Cart getCart(String userId){
        //connect to db
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            // prepared sql statement
            String sql = "SELECT * FROM carts WHERE user_id=?";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, userId);
                //execute query
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        // create instance of cart and return cart.
                        Cart cart = new Cart();
                        cart.setId(rs.getString("id"));
                        cart.setUser_id(rs.getString("user_id"));

                        return cart;
                    }
                }
            }            
        } catch(SQLException e){
            throw new RuntimeException("Unable to connect to database. Error code: " + e.getMessage());
        } catch(IOException e){
            throw new RuntimeException("Unable to find application.properties");
        } catch(ClassNotFoundException e){
            throw new RuntimeException("Unable to load jdbc");
        }
        //return null if no record found.
        return null;
    }
   
    public static List<Product> getCartItems(String CartId){
        List<Optional<Product>> productsOpt = new ArrayList<Optional<Product>>();
        List<Product> listProducts = new ArrayList<Product>();
        //create connection to db
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            // I learned this little trick in college. It takes the things I need from both the cartitems and products table 
            //and leverages the fact that they both share a relationship
            String sql = "select cartitems.id, qty, cartitems.price, products.name, qty_on_hand, description from cartitems inner join products on cartitems.product_id = products.id";
            //create preparedStatement
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                //ps.setString(1, CartId);
                //execute query
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next())
                    {
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        product.setQty_on_hand(rs.getInt("qty_on_hand"));

                        productsOpt.add(Optional.of(product));
                    }
                }
                if (productsOpt.isEmpty()){
                    System.out.println("Returning null");
                    return null; 
                } else {
                    int i = 0;
                    for (Optional<Product> productOpt : productsOpt) {
                        productOpt = productsOpt.get(i++);
                        Product prod = productOpt.get();
                        listProducts.add(prod);
                    } 
        
                    return listProducts;  
                }        
            }
        } catch(SQLException e){
            throw new RuntimeException("Unable to connect to database. Error code: " + e.getMessage());
        } catch(IOException e){
            throw new RuntimeException("Unable to find application.properties");
        } catch(ClassNotFoundException e){
            throw new RuntimeException("Unable to load jdbc");
        }
    }


    public void addToCart(Product product, int quantity)
    {
        try(Connection conn = ConnectionFactory.getInstance().getConnection())
        {
            String sql = "INSERT INTO cartitems(id, qty, name, price, cart_id, product_id) VALUES (?,?,?,?,?,?)";
            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1, UUID.randomUUID().toString());
                ps.setInt(2, quantity);
                ps.setString(3, product.getName());
                ps.setDouble(4, product.getPrice());
                ps.setString(5, CartService.getCartId());
                ps.setString(6, product.getId());
                ps.executeUpdate();

            }
        }
        catch(SQLException e){
            throw new RuntimeException("Unable to connect to database. Error code: " + e.getMessage());
        } 
        catch(IOException e)
        {
            throw new RuntimeException("Unable to find application.properties");
        } 
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException("Unable to load jdbc");
        }

    }

    /*public static ResultSet getItemsFromCart(String cart_id)
    {
        try(Connection conn = ConnectionFactory.getInstance().getConnection())
        {
            

            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1, cart_id);

                try(ResultSet rs = ps.executeQuery())
                {
                    while(rs.next())
                    {

                    }
                }
            }
        }
        catch(SQLException e){
            throw new RuntimeException("Unable to connect to database. Error code: " + e.getMessage());
        } 
        catch(IOException e)
        {
            throw new RuntimeException("Unable to find application.properties");
        } 
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException("Unable to load jdbc");
        }
    }*/

    public void addCartToDB(Cart cart, String user_id)
    {
        try(Connection conn = ConnectionFactory.getInstance().getConnection())
        {
            String sql = "INSERT INTO cart(id, user_id) VALUES (?,?)";

            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1, cart.getId());
                ps.setString(2, user_id);
                ps.executeUpdate();
                System.out.println("Added cart to database");
            }
        }   
        catch(SQLException e){
            throw new RuntimeException("Unable to connect to database. Error code: " + e.getMessage());
        } 
        catch(IOException e)
        {
            throw new RuntimeException("Unable to find application.properties");
        } 
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException("Unable to load jdbc");
        }
    }

    public void deleteFromCart(String name)
    {
        String sql = "DELETE FROM cartitems WHERE name = ?";
        try(Connection conn = ConnectionFactory.getInstance().getConnection())
        {
            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1,name);
                ps.execute();

            }
            
        }
        catch(SQLException e){
            throw new RuntimeException("Unable to connect to database. Error code: " + e.getMessage());
        } 
        catch(IOException e)
        {
            throw new RuntimeException("Unable to find application.properties");
        } 
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException("Unable to load jdbc");
        }
        
    }

    public void updateQuantity(String name, int quantity)
    {
        
        try(Connection conn = ConnectionFactory.getInstance().getConnection())
        {
            String sql = "UPDATE cartitems Set qty = ? WHERE name = ?";
            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setInt(1, quantity);
                ps.setString(2, name);
                ps.executeUpdate();
            }
        }
        catch(SQLException e){
            throw new RuntimeException("Unable to connect to database. Error code: " + e.getMessage());
        } 
        catch(IOException e)
        {
            throw new RuntimeException("Unable to find application.properties");
        } 
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException("Unable to load jdbc");
        }
    }
}
