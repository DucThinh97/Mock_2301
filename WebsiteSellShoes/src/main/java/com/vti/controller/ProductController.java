package com.vti.controller;

import com.vti.dto.ProductDTO;
import com.vti.entity.Product;
import com.vti.form.creating.ProductFormForCreating;
import com.vti.form.filter.ProductFilter;
import com.vti.form.updating.ProductFormForUpdating;
import com.vti.service.implement.IProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private IProductService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<?> getAllProducts(
            Pageable pageable,
            ProductFilter filter,
            @RequestParam(name = "search", required = false)
            String search) {
        Page<Product> entities = service.getAllProducts(pageable, filter, search);

        // convert entities --> dtos
        List<ProductDTO> dtos = modelMapper.map(entities.getContent(), new TypeToken<List<ProductDTO>>() {
        }.getType());

        Page<ProductDTO> dtoPages = new PageImpl<>(dtos, pageable, entities.getTotalElements());

        return new ResponseEntity<>(dtoPages, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable(name = "id") short id) {

        Product product = service.getProductByID(id);

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);



        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody ProductFormForCreating form) {
        service.createProduct(form);
        return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "id") int id, @RequestBody ProductFormForUpdating form) {
        service.updateProduct(id, form);
        return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{ids}")
    public ResponseEntity<?> deleteProducts(@PathVariable(name = "ids") List<Integer> ids) {
        service.deleteProduct(ids);
        return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
    }
}
