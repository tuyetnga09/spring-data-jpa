package com.example.asignment_test.service.cart;

import com.example.asignment_test.entity.ProductDetail;
import com.example.asignment_test.entity.cart.CartDetail;
import com.example.asignment_test.repository.cart.CartDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailService {
    private final CartDetailRepository cartDetailRepository;

    public CartDetailService(CartDetailRepository cartDetailRepository) {
        this.cartDetailRepository = cartDetailRepository;
    }

    public CartDetail addToCart(ProductDetail product, int number) {
        CartDetail cartItem = new CartDetail();
        cartItem.setProduct(product);
        cartItem.setNumber(number);
        return cartDetailRepository.save(cartItem);
    }

    public List<CartDetail> getAllCartItems() {
        return cartDetailRepository.findAll();
    }

}
