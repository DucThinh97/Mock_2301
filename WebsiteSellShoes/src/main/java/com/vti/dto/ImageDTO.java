package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ImageDTO {

    @NonNull
    private int id;

    @NonNull
    private String image1;

    @NonNull
    private String image2;

    @NonNull
    private String image3;

    @NonNull
    private String image4;

    @NonNull
    private String image5;

    @NonNull
    private String image6;

    @NonNull
    private String productId;
}
