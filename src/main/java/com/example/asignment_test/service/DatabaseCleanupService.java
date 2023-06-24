package com.example.asignment_test.service;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DatabaseCleanupService {
    @PersistenceContext
    private EntityManager entityManager;

    public void deleteUsersRolesTable() {
        try {
            entityManager.createNativeQuery("DROP TABLE users_roles").executeUpdate();
            System.out.println("Xoá bảng 'users_roles' thành công");
        } catch (Exception e) {
            System.out.println("Lỗi khi xoá bảng 'users_roles': " + e.getMessage());
        }
    }
}
