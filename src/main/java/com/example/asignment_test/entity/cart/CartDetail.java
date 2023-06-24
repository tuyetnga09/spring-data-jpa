package com.example.asignment_test.entity.cart;

import com.example.asignment_test.entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
