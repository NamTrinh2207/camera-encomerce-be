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
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    @Query(nativeQuery = true, value = "select r.name, count(users.username) as 'number' from users join user_roles ur on users.id = ur.user_id join role r on r.id = ur.role_id group by r.name;")
    Iterable<ICountRole> getRoleNumber();
}
