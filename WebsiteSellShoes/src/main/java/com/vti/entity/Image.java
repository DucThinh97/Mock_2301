package com.vti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`Image`")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    private Product product;

    @Column(name = "image1", length = 100)
    private String image1;

    @Column(name = "image2", length = 100)
    private String image2;

    @Column(name = "image3", length = 100)
    private String image3;

    @Column(name = "image4", length = 100)
    private String image4;

    @Column(name = "image5", length = 100)
    private String image5;

    @Column(name = "image6", length = 100)
    private String image6;

}