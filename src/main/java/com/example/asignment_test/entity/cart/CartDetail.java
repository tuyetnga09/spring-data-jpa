package com.example.asignment_test.entity.cart;

import com.example.asignment_test.entity.ProductDetail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Cart_Detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Number")
    private Integer number;

    @Column(name = "Unit_price")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "ID_Product_Details")
    private ProductDetail product;

    @ManyToOne
    @JoinColumn(name = "ID_Cart")
    private Cart cart;
}
