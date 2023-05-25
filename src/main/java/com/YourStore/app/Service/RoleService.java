package com.YourStore.app.Service;

import java.util.Optional;

import com.YourStore.app.DAO.RoleDAO;
import com.YourStore.app.Exceptions.RoleNotFoundException;
import com.YourStore.app.Model.Role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleService {
        private final RoleDAO roledao;

        public Role findByName(String name)
        {
            Optional<Role> roleOpt = roledao.findByName(name);

            if(roleOpt.isEmpty())
            {
                throw new RoleNotFoundException();
            }

            return roleOpt.get();
        }
        

}
