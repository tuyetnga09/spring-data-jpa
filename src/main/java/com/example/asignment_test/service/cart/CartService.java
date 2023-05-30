package com.example.asignment_test.service.cart;

import com.example.asignment_test.entity.bill.Bill;
import com.example.asignment_test.entity.cart.Cart;
import com.example.asignment_test.entity.cart.CartDetail;
import com.example.asignment_test.repository.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;


}
