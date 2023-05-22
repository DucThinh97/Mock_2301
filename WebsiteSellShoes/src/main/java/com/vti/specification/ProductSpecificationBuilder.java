package com.vti.specification;

import com.vti.entity.Product;
import com.vti.form.filter.ProductFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ProductSpecificationBuilder {

	private ProductFilter filter;
	private String search;

	public ProductSpecificationBuilder(ProductFilter filter, String search) {
		this.filter = filter;
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<Product> build() {

		SearchCriteria searchCriteria = new SearchCriteria("name", "Like", search);
		SearchCriteria minSalePriceCriteria = new SearchCriteria("salePrice", ">=", filter.getMinSalePrice());
		SearchCriteria maxSalePriceCriteria = new SearchCriteria("salePrice", "<=", filter.getMaxSalePrice());

		Specification<Product> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new ProductSpecification(searchCriteria);
		}

		// min totalMember filter
		if (filter.getMinSalePrice() != 0) {
			if (where != null) {
				where = where.and(new ProductSpecification(minSalePriceCriteria));
			} else {
				where = new ProductSpecification(minSalePriceCriteria);
			}
		}

		// max totalMember filter
		if (filter.getMaxSalePrice() != 0) {
			if (where != null) {
				where = where.and(new ProductSpecification(maxSalePriceCriteria));
			} else {
				where = new ProductSpecification(maxSalePriceCriteria);
			}
		}

		return where;
	}
}
