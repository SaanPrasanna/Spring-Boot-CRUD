package com.example.demo.repo;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = "SELECT address FROM user WHERE id = ? LIMIT 1", nativeQuery = true)
    String findAddressByID(int id);
}
