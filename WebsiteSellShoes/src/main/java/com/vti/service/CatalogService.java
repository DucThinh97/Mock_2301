package com.vti.service;

import com.vti.form.creating.CatalogFormForCreating;
import com.vti.form.updating.CatalogFormForUpdating;
import com.vti.entity.Catalog;
import com.vti.repository.ICatalogRepository;
import com.vti.service.implement.ICatalogService;
import com.vti.specification.CatalogSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatalogService implements ICatalogService {

	@Autowired
	private ICatalogRepository repository;

	@Override
	public Page<Catalog> getAllCatalogs(Pageable pageable, String search) {

		CatalogSpecificationBuilder specification = new CatalogSpecificationBuilder(search);

		return repository.findAll(specification.build(),pageable);
	}

	@Override
	public Catalog getCatalogByID(int id) {
		return repository.findById(id).get();
	}

	@Override
	public void createCatalog(CatalogFormForCreating form) {
		repository.save(form.toEntity());

	}

	@Override
	public void updateCatalog(int id, CatalogFormForUpdating form) {
		Catalog entity = repository.findById(id).get();
		entity.setName(form.getName());
		repository.save(entity);
	}

	@Override
	public void deleteCatalog(int id) {
		repository.deleteById(id);
	}


}
