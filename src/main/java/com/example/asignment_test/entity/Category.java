package com.example.asignment_test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Category")
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Code")
    private String code;
    @Column(name = "Name")
    private String name;
    @Column(name = "Status")
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "ID_Color")
    private Color color;
    @ManyToOne
    @JoinColumn(name = "ID_Capacity")
    private Capacity capacity;
    @ManyToOne
    @JoinColumn(name = "ID_Product_Line")
    private ProductLine productLine;

    public Category() {
        this.status = 0;
    }
}
