package com.revature.Project0.repositories;

import com.revature.Project0.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CatRepo extends JpaRepository<Cat, Integer> {
    List<Cat> findByOwnerId(int ownerId);
    @Query("SELECT COUNT(id) FROM Cat where id=:id")
    Integer idExists(@Param("id") Integer id);
}
