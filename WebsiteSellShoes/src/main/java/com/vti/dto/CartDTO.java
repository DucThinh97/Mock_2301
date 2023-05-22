package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CartDTO {

    @NonNull
    private String productId;
    @NonNull
    private ImageDTO productImage;
    @Data
    @NoArgsConstructor
    static class ImageDTO {
        private String image1;

    }
    @NonNull
    private String productName;

    @NonNull
    private String productSize;

    @NonNull
    private int quantity;

    @NonNull
    private int productPrice;

    @NonNull
    private int productSalePrice;




}
