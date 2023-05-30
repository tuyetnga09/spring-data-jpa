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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    @Column(name = "Images")
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
