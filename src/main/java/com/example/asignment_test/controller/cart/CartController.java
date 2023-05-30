package com.example.asignment_test.controller.cart;

import com.example.asignment_test.entity.ProductDetail;
import com.example.asignment_test.entity.cart.Cart;
import com.example.asignment_test.entity.cart.CartDetail;
import com.example.asignment_test.repository.ProducDetailRepository;
import com.example.asignment_test.service.ProductDetailService;
import com.example.asignment_test.service.cart.CartDetailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartDetailService cartDetailService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProducDetailRepository producDetailRepository;

    public CartController(CartDetailService cartItemService) {
        this.cartDetailService = cartItemService;
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity, HttpSession session) {
        // Lấy thông tin sản phẩm từ bảng sản phẩm
        Optional<ProductDetail> productOptional = producDetailRepository.findById(id);
        if (!productOptional.isPresent()) {
            // Xử lý khi sản phẩm không tồn tại
            return "redirect:/error"; // Điều hướng đến trang lỗi
        }
        ProductDetail product = productOptional.get();

        // Lấy giỏ hàng từ session hoặc tạo mới nếu chưa có
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        boolean isProductExists = false;
        for (CartDetail cartItem : cart.getCartDetail()) {
            if (cartItem.getProduct().getId().equals(id)) {
                // Sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng và tổng tiền
                int newQuantity = cartItem.getNumber() + quantity;
                cartItem.setNumber(newQuantity);
                cartItem.setTotalPrice((double) (product.getCategory().getProductLine().getPrice()  * newQuantity));
                isProductExists = true;
                break;
            }
        }

        if (!isProductExists) {
            // Sản phẩm chưa tồn tại trong giỏ hàng, thêm sản phẩm mới
            CartDetail cartItem = new CartDetail();
            cartItem.setProduct(product);
            cartItem.setNumber(quantity);
            cartItem.setTotalPrice((double) (product.getCategory().getProductLine().getPrice() * quantity));
            cartItem.setCart(cart);
            cart.getCartDetail().add(cartItem);
        }

        return "redirect:/cart";
    }

    @GetMapping
    public String viewCart(Model model, HttpSession session) {
        // Lấy giỏ hàng từ session
        Cart cart = (Cart) session.getAttribute("cart");

        // Tính tổng tiền của đơn hàng
        double totalAmount = 0.0;
        if (cart != null) {
            for (CartDetail cartItem : cart.getCartDetail()) {
                totalAmount += cartItem.getTotalPrice();
            }
        }
        // Đưa tổng tiền vào model
        model.addAttribute("cartItems", cart != null ? cart.getCartDetail() : Collections.emptyList());
        model.addAttribute("totalAmount", totalAmount);

        return "cart";
    }

    @PostMapping("/increase")
    public String increaseQuantity(@RequestParam("productId") Integer productId, HttpSession session) {
        // Lấy giỏ hàng từ session
        Cart cart = (Cart) session.getAttribute("cart");

        // Tìm kiếm sản phẩm trong giỏ hàng
        if (cart != null) {
            for (CartDetail cartItem : cart.getCartDetail()) {
                if (cartItem.getProduct().getId().equals(productId)) {
                    // Tăng số lượng và tính lại tổng tiền
                    int newQuantity = cartItem.getNumber() + 1;
                    cartItem.setNumber(newQuantity);
                    cartItem.setTotalPrice((double) (cartItem.getProduct().getCategory().getProductLine().getPrice() * newQuantity));
                    break;
                }
            }
        }

        return "redirect:/cart";
    }
    @PostMapping("/decrease")
    public String decreaseQuantity(@RequestParam("productId") Integer productId, HttpSession session) {
        // Lấy giỏ hàng từ session
        Cart cart = (Cart) session.getAttribute("cart");

        // Tìm kiếm sản phẩm trong giỏ hàng
        if (cart != null) {
            for (CartDetail cartItem : cart.getCartDetail()) {
                if (cartItem.getProduct().getId().equals(productId)) {
                    // Giảm số lượng và tính lại tổng tiền
                    int newQuantity = cartItem.getNumber() - 1;
                    if (newQuantity >= 1) {
                        cartItem.setNumber(newQuantity);
                        cartItem.setTotalPrice((double) (cartItem.getProduct().getCategory().getProductLine().getPrice() * newQuantity));
                    }
                    break;
                }
            }
        }

        return "redirect:/cart";
    }


}
