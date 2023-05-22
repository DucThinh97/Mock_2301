package com.vti.service.implement;

import com.vti.form.creating.ProductFormForCreating;
import com.vti.form.updating.ProductFormForUpdating;
import com.vti.form.filter.ProductFilter;
import com.vti.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

	Page<Product> getAllProducts(Pageable pageable, ProductFilter filter, String search);

	Product getProductByID(int id);

	void createProduct(ProductFormForCreating form);

	void updateProduct(int id, ProductFormForUpdating form);

	void deleteProduct(List<Integer> ids);

	Page<Product>getNameAndPriceByOderId(int oderId);

}
