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
@Table(name = "OderDetail")
public class OderDetailDBConvert implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "oderDetailId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConvert;

    @ManyToOne
    @JoinColumn(name = "oderId", nullable = false)
    private OderList oderList;

    @Column(name = "productName", nullable = false, length = 30)
    private String productName;

    @Column(name = "salePrice")
    private int price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total", nullable = false)
    private int total;


}