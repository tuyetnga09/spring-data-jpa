package com.example.asignment_test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Product_Line")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Code")
    private String code;
    @Column(name = "Name")
    private String name;
    @Column(name = "Import_price")
    private Float importPrice;
    @Column(name = "Price")
    private Float price;
    @Column(name = "Date_Create")
    private Date dateCreate;
    @Column(name = "Date_correct")
    private Date dateCorrect;
    @Column(name = "Status")
    private Integer status;

    @ManyToOne
    @JoinColumn( name = "ID_Manufacturer")
    private Manufacturer manufacturer;
    @PrePersist
    public void prePersist() {
        this.dateCreate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        if (this.dateCreate == null) {
            this.dateCreate = new Date();
        }
        this.dateCorrect = new Date();
    }

}
