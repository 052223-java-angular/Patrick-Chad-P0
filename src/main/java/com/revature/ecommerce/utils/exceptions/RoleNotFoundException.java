package com.revature.ecommerce.utils.exceptions;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(){
        super("Role not found.");
    }
}
