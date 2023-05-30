package com.example.asignment_test.repository.cart;

import com.example.asignment_test.entity.cart.Cart;
import com.example.asignment_test.entity.cart.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    List<CartDetail> findByCart(Cart cart);
}
