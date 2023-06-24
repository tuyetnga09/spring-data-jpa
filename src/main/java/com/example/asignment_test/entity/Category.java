package com.example.asignment_test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
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
import org.springframework.mock.web.MockMultipartFile;

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
