package com.vti.specification;

import com.vti.entity.Catalog;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class CatalogSpecificationBuilder {
    private String search;

    public CatalogSpecificationBuilder(String search) {
        this.search = search;
    }

    @SuppressWarnings("deprecation")
    public Specification<Catalog> build() {

        SearchCriteria searchCriteria = new SearchCriteria("name", "Like", search);

        Specification<Catalog> where = null;

        // search
        if (!StringUtils.isEmpty(search)) {
            where = new CatalogSpecification(searchCriteria);
        }

        return where;
    }
}
