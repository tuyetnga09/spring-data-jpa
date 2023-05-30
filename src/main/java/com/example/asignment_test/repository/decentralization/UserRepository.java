package com.example.asignment_test.repository.decentralization;

import com.example.asignment_test.entity.decentralization.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
