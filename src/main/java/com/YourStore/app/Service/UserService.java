package com.YourStore.app.Service;

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

    public User register(String username, String password)
    {
        
    }
}
