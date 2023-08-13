package com.example.cameraincome.service.user;


import com.example.cameraincome.model.DTO.ICountRole;
import com.example.cameraincome.model.user.Users;
import com.example.cameraincome.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface IUserService extends IGeneralService<Users>, UserDetailsService {
    Users findByUsername(String name);
    Users findByEmail(String email);
    Iterable<ICountRole> getRoleNumber();
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
