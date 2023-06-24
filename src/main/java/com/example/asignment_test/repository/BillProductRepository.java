package com.example.asignment_test.repository;

import com.example.asignment_test.entity.bill.BillProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillProductRepository extends JpaRepository<BillProduct, Integer> {
}
