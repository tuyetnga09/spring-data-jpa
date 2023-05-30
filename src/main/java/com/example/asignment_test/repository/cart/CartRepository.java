package com.example.asignment_test.repository.cart;

import com.example.asignment_test.entity.bill.Bill;
import com.example.asignment_test.entity.cart.Cart;
import com.example.asignment_test.entity.cart.CartDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepository  {
    private List<CartDetail> cartItems = new ArrayList<>();

    public List<CartDetail> getCartItems() {
        return cartItems;
    }

}
