package com.example.asignment_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Code")
    private String code;

    @Column(name = "full-name")
    private String fullName;

    @Column(name = "Gender")
    private Integer gender;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Adrress")
    private String address;

    @Column(name = "email")
    private String email;
    @Column(name = "status")
    private Integer status;

    @Column(name = "Date_Create")
    private Date dateCreate;

    public Customer(String fullName, String phoneNumber, String address) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
