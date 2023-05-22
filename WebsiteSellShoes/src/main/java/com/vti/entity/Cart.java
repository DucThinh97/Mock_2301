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
@Table(name = "Cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;


    @EmbeddedId
    private ShoppingCartKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class ShoppingCartKey implements Serializable {

        private static final long serialVersionUID = 1L;

        @Column(name = "userId")
        private int userId;

        @Column(name = "productId")
        private int productId;


    }

}