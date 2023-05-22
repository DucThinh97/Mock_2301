package com.vti.controller;

import com.vti.dto.CartDTO;
import com.vti.entity.Cart;
import com.vti.form.creating.CartFormForCreating;
import com.vti.form.updating.CartFormForUpdating;
import com.vti.repository.IProductRepository;
import com.vti.repository.IUserRepository;
import com.vti.service.implement.ICartService;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping(value = "api/v1/carts")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private ICartService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<?> getAllProducts(Pageable pageable) {
        Page<Cart> entities = service.getAllCarts(pageable);

        // convert entities --> dtos
        List<CartDTO> dtos = modelMapper.map(entities.getContent(), new TypeToken<List<CartDTO>>() {
        }.getType());

        Page<CartDTO> dtoPages = new PageImpl<>(dtos, pageable, entities.getTotalElements());

        return new ResponseEntity<>(dtoPages, HttpStatus.OK);
    }

    // get toàn bộ sản phẩm(giỏ hàng) của user
    @GetMapping(value = "/userId/{userId}")
    public ResponseEntity<?> getCartByUserId(Pageable pageable, @PathVariable(name = "userId") int userId) {

        Page<Cart> cart = service.getCartByUserId(pageable, userId);

        // convert entities --> dtos
        List<CartDTO> dtos = modelMapper.map(cart.getContent(), new TypeToken<List<CartDTO>>() {
        }.getType());

        Page<CartDTO> dtoPages = new PageImpl<>(dtos, pageable, cart.getTotalElements());

        return new ResponseEntity<>(dtoPages, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<?> createCart(@RequestBody CartFormForCreating form) {
        service.createCart(form);
        return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
    }

    // update số lượng sản phẩm
    @PutMapping()
    public ResponseEntity<?> updateQuantityInCart(@Parameter(name = "id") int productId, @Parameter(name = "id") int userId, @RequestBody CartFormForUpdating form) {
        service.updateQuantityInCart(productId, userId, form);
        return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
    }

    // delete toàn bộ sản phẩm(giỏ hàng) của user
    @DeleteMapping(value = "/userId/{userId}")
    public ResponseEntity<?> deleteCartByUserId(@PathVariable(name = "userId") int userId) {
        service.deleteCartByUserId(userId);
        return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
    }

    // delete 1 sản phẩm
    @DeleteMapping(value = "/productId/{productId}")
    public ResponseEntity<?> deleteProductInCartByProductId(@PathVariable(name = "productId") int productId) {
        service.deleteProductInCartByProductId(productId);
        return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
    }

    // tính tổng
    @GetMapping(value = "/sum")
    public ResponseEntity<?> total(@Parameter(name = "userId") int userId) {
        int sum = service.total(userId);
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }


}
