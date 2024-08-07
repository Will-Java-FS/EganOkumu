package com.revature.Project0.repositories;

import com.revature.Project0.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("SELECT COUNT(username) FROM User where username = :username")
    Integer userExists(@Param("username") String username);

    @Query("SELECT COUNT(id) FROM User where id=:id")
    Integer idExists(@Param("id") Integer id);
}
