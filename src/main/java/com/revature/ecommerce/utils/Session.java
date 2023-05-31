package com.revature.ecommerce.utils;

import com.revature.ecommerce.models.User;
import com.revature.ecommerce.models.Cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Session {
    // 1, 2, 3, 4...... 10000
    private String id;
    private String username;
    private String roleId;
    private String cart_id;

    public void setSession(User user) {
        //System.out.println("In setSession()");
        this.id = user.getId();
        this.username = user.getUsername();
        this.roleId = user.getRole_id();
    }

    public void setCartId(String cart_id)
    {
       //System.out.println("Set the cart id");
        this.cart_id = cart_id;
    }

}
