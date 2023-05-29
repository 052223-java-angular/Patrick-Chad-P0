package com.revature.ecommerce;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.ecommerce.daos.CartDAO;
import com.revature.ecommerce.models.Cart;
import com.revature.ecommerce.models.Order;
import com.revature.ecommerce.models.User;
import com.revature.ecommerce.services.CartService;
import com.revature.ecommerce.services.UserService;

/**
 * Unit test for simple App.
 */
public class AppTest{ 
    private User user = new User();
    private Cart cart = new Cart();
    private Scanner scan = new Scanner(System.in);
   
    @Mock
    private CartService cartService;
    
    @Mock
    private UserService userService;

    @Before
    public void setup(){
        //initialze user and cart
        userService.register("testUser", BCrypt.hashpw("Passw0rd", BCrypt.gensalt()));
        CartService.createCart();
        MockitoAnnotations.openMocks(this);
    }


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     *  Tests checkout routine.
     */
    @Test
    public void testCheckout(){
        //initialze user and cart
        //User user = new User("cd7a196a-b4a1-4f2a-a6fc-902cc887ab71","testUser", BCrypt.hashpw("Passw0rd", BCrypt.gensalt()));
        Cart cart = new Cart(UUID.randomUUID().toString(),"cd7a196a-b4a1-4f2a-a6fc-902cc887ab71");
        Order order = cartService.Checkout(scan, cart);
        assertEquals("Cart not saved", cart.getId(), order.getCart_id());

    }
}
