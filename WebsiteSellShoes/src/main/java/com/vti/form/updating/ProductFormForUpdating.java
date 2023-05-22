package com.vti.form.updating;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductFormForUpdating {

	private String name;

	private String describe;

	private String size;

	private short amount;

	private int purchasePrice;

	private int price;

	private int salePrice;

}
