//package com.vti.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "CreatorProduct")
//public class CreatorProduct implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//
//    @EmbeddedId
//    private CreatorProductKey id;
//
//    @ManyToOne
//    @MapsId("staffId")
//    @JoinColumn(name = "staffId")
//    private User user;
//
//    @ManyToOne
//    @MapsId("productId")
//    @JoinColumn(name = "productId")
//    private Product product;
//
//
//
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Embeddable
//    public static class CreatorProductKey implements Serializable {
//
//        private static final long serialVersionUID = 1L;
//
//        @Column(name = "staffId")
//        private int staffId;
//
//        @Column(name = "productId")
//        private int productId;
//
//
//    }
//
//}