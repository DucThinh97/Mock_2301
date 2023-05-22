package com.vti.form.creating;

import com.vti.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductFormForCreating {

    private String name;

    private int catalogId;

    private String describe;

    private String size;

    private short amount;

    private int purchasePrice;

    private int price;

    private int salePrice;

    private String review;


}
