package com.example.cameraincome.repo;

import com.example.cameraincome.model.DTO.ICountRole;
import com.example.cameraincome.model.user.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByUsername(String name);
    Optional<Users> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    @Query(nativeQuery = true, value = "select u.name,count(r.name) as number from users u join roles r on u.id = r.id group by u.name")
    Iterable<ICountRole> getRoleNumber();
}
