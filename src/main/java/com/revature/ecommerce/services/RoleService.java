package com.revature.ecommerce.services;

import java.util.Optional;

import com.revature.ecommerce.daos.RoleDAO;
import com.revature.ecommerce.models.Role;
import com.revature.ecommerce.utils.exceptions.RoleNotFoundException;

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
