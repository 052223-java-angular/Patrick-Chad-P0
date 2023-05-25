package com.revature.ecommerce.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.ecommerce.daos.UserDAO;
import com.revature.ecommerce.models.Role;
import com.revature.ecommerce.models.User;


public class UserService {
    private final UserDAO userdao;
    private final RoleService roleservice;

    private UserService(UserDAO userdao, RoleService roleservice)
    {
        this.userdao = userdao;
        this.roleservice = roleservice;
    }

    public User register(String username, String password)
    {
        Role foundRole = roleservice.findByName("USER");
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed, foundRole.getId());
        userdao.update(newUser);
        return newUser;


        
    }


   public boolean isValidUsername(String username)
   {
     return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
   }

   public boolean isUniqueUsername(String username)
   {
        Optional<User> userOpt = userdao.findUserByUsername(username);

        if(userOpt.isEmpty())
        {
            return true;
        }
        
        return false;
   }
    public boolean isValidPassword(String password)
    {
         return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }
   public boolean isSamePassword(String password, String confirmPassword)
   {
        return password.equals(confirmPassword);
   }

   public get

}
