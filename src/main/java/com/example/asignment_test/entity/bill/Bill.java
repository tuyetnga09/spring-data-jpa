package com.example.asignment_test.entity.bill;

import com.example.asignment_test.entity.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Bill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Code")
    private String code;

    @Column(name = "Purchase_date")
    private Date purchaseDate;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Adrress")
    private String address;

    @Column(name = "Status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "ID_Customer")
    private Customer customer;

    @OneToMany(mappedBy = "bill")
    private List<BillProduct> billProducts;
}
