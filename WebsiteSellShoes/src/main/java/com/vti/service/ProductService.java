package com.vti.service;

import com.vti.entity.Product;
import com.vti.form.creating.ProductFormForCreating;
import com.vti.form.filter.ProductFilter;
import com.vti.form.updating.ProductFormForUpdating;
import com.vti.repository.IProductRepository;
import com.vti.service.implement.IProductService;
import com.vti.specification.ProductSpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProductRepository repository;

    @Override
    public Page<Product> getAllProducts(Pageable pageable, ProductFilter filter, String search) {

        ProductSpecificationBuilder specification = new ProductSpecificationBuilder(filter, search);

        return repository.findAll(specification.build(), pageable);
    }

    @Override
    public Product getProductByID(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void createProduct(ProductFormForCreating form) {
        // omit id field
        TypeMap<ProductFormForCreating, Product> typeMap = modelMapper.getTypeMap(ProductFormForCreating.class, Product.class);
        if (typeMap == null) { // if not already added
            // skip field
            modelMapper.addMappings(new PropertyMap<ProductFormForCreating, Product>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        // convert form to entity
        Product product = modelMapper.map(form, Product.class);
//        product.setCreateDate(new Date());
        repository.save(product);

    }

    @Override
    public void updateProduct(int id, ProductFormForUpdating form) {
        Product entity = repository.findById(id).get();
        entity.setName(form.getName());
        entity.setDescribe(form.getDescribe());
        entity.setSize(form.getSize());
        entity.setAmount(form.getAmount());
        entity.setPurchasePrice(form.getPurchasePrice());
        entity.setPrice(form.getPrice());
        entity.setSalePrice(form.getSalePrice());
        repository.save(entity);
    }


    @Override
    public void deleteProduct(List<Integer> ids) {
        repository.deleteByIdIn(ids);

    }

    @Override
    public Page<Product> getNameAndPriceByOderId(int oderId) {


        return null;
    }
}
