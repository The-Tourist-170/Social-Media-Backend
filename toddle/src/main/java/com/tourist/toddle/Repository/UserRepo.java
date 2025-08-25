package com.tourist.toddle.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tourist.toddle.Models.User;

public interface UserRepo extends JpaRepository<User, Integer>{

    public User findByEmail(String email);

    @Query("select u from User u where u.fName LIKE %:query% OR u.lName LIKE %:query% OR u.email LIKE %:query%")
    public List<User> searchUser(@Param("query") String query);
    
}