package com.vti.form.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilter {

    private int minSalePrice;

    private int maxSalePrice;


}
