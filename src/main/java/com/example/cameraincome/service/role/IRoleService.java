package com.example.cameraincome.service.role;

import com.example.cameraincome.model.user.Roles;
import com.example.cameraincome.service.IGeneralService;

import java.util.Set;

public interface IRoleService extends IGeneralService<Roles> {
    Roles findByName(String name);
    Set<Roles> getRolesByName(Set<String> roleNames);
}
