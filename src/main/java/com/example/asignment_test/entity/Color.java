package com.example.asignment_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Color")
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Code")
    private String code;
    @Column(name = "Name")
    private String name;
    @Column(name = "Date_Create")
    private Date dateCreate;
    @Column(name = "Date_correct")
    @Temporal(TemporalType.DATE)
    private Date dateCorrect;
    @Column(name = "Status")
    private Integer status;

    @Column(name = "images")
    private String images;

    public Color(){
        this.status = 0;
    }
}
