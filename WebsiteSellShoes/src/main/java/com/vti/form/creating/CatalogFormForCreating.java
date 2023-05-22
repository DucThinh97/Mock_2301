package com.vti.form.creating;

import com.vti.entity.Catalog;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CatalogFormForCreating {

	private String name;

	public Catalog toEntity() {
		return new Catalog(name);
	}

}
