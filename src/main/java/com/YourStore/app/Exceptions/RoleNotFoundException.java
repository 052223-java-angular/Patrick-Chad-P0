package com.YourStore.app.Exceptions;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(){
        super("Role not found.");
    }
}
