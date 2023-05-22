package com.vti.service.implement;

import com.vti.form.creating.CatalogFormForCreating;
import com.vti.form.updating.CatalogFormForUpdating;
import com.vti.entity.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICatalogService {

	Page<Catalog> getAllCatalogs(Pageable pageable, String search);

	Catalog getCatalogByID(int id);

	void createCatalog(CatalogFormForCreating form);

	void updateCatalog(int id, CatalogFormForUpdating form);

	void deleteCatalog(int id);

}
