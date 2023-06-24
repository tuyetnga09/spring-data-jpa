package com.example.asignment_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Table(name = "Product_Line")
@AllArgsConstructor
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

    public ProductLine() {
        this.status = 0;
    }
}
