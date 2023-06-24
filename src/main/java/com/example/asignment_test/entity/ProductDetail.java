package com.example.asignment_test.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Product_Details")
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Code")
    private String code;
    @Column(name = "Name")
    private String name;
    @Column(name = "Status")
    private Integer status;
    @Column(name = "Number")
    private Integer number;
    @Column(name = "images")
    private String images;
    @Column(name = "Date_Create")
    private Date dateCreate;
    @Column(name = "Date_Conrrect")
    @Temporal(TemporalType.DATE)
    private Date dateCorrect;

    @ManyToOne
    @JoinColumn(name = "ID_Category")
    private Category category;

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

    public ProductDetail() {
        this.status = 0;
    }
}
