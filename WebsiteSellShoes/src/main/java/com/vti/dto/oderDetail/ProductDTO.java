package com.vti.dto.oderDetail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductDTO {

    @NonNull
    private String productName;

    @NonNull
    private int price;


}
