package com.example.asignment_test.repository;

import com.example.asignment_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UseRepository  extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email")
    User fetchUserRolesByEmail(@Param("email") String email);
}
