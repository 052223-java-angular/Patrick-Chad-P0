package com.YourStore.app.Service;

import java.util.Optional;

import com.YourStore.app.DAO.UserDAO;
import com.YourStore.app.Model.User;

public class UserService {
    private final UserDAO userdao;
    private final RoleService roleservice;

    public UserService(UserDAO userdao, RoleService roleservice)
    {
        this.userdao = userdao;
        this.roleservice = roleservice;
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

   public boolean isSamePassword(String password, String confirmPassword)
   {
        return password.equals(confirmPassword);
   }
}
