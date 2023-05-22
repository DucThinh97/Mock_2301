package com.vti.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OderDetailDTO {

    @NonNull
    private String productName;

    @NonNull
    private int price;

    @NonNull
    @JsonProperty("Amount")
    private int productCartsQuantity;

    @NonNull
    private int ToTal;


//    public int Total(int price, int productCartsQuantity) {
//        return price * productCartsQuantity;
//    }
}
