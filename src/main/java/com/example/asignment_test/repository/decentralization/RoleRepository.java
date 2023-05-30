package com.example.asignment_test.repository.decentralization;

import com.example.asignment_test.entity.decentralization.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
