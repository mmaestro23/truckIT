package com.maestro.truckIT.repo;

import com.maestro.truckIT.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepo extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
    Users findByUsername(String username);

    @Query("select u from users u where u.username=:username")
    public Users getUserByUsername(@Param("username") String username);

    public long countById(Integer id);

    Users findByEmailAndPassword(String email, String password);

}
