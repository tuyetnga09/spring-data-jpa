package com.example.asignment_test.entity;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.mock.web.MockMultipartFile;
import java.util.Date;

@Entity
@Table(name = "Capacity")
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Capacity {
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
    public Capacity() {

        this.status = 0;
    }

}
