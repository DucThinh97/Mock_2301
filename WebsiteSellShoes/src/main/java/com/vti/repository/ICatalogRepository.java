package com.vti.repository;

import com.vti.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICatalogRepository extends JpaRepository<Catalog, Integer>, JpaSpecificationExecutor<Catalog> {

}
