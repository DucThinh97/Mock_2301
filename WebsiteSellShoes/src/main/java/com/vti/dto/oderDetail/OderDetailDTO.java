package com.vti.dto.oderDetail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OderDetailDTO {

    @NonNull
    private String productName;

    @NonNull
    private int price;

    @NonNull
    private int quantity;

    @NonNull
    private int total;


}
