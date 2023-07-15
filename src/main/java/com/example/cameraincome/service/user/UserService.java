package com.example.cameraincome.service.user;

import com.example.cameraincome.model.DTO.ICountRole;
import com.example.cameraincome.model.DTO.UserPrinciple;
import com.example.cameraincome.model.user.Users;
import com.example.cameraincome.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository appUserRepo;

    @Override
    public Iterable<Users> findAll() {
        return appUserRepo.findAll();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return appUserRepo.findById(id);
    }

    @Override
    public Users save(Users users) {
        return appUserRepo.save(users);
    }

    @Override
    public void remove(Long id) {
        appUserRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userOptional = appUserRepo.findByUsername(username);
        return userOptional.map(UserPrinciple::build).orElse(null);
    }

    @Override
    public Users findByUsername(String name) {
        return appUserRepo.findByUsername(name).get();
    }

    @Override
    public Iterable<ICountRole> getRoleNumber() {
        return appUserRepo.getRoleNumber();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return appUserRepo.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return appUserRepo.existsByEmail(email);
    }
}
