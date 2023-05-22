package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CatalogDTO {

    @NonNull
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String image;

}
