package com.example.cameraincome.repo;

import com.example.cameraincome.model.user.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
}
