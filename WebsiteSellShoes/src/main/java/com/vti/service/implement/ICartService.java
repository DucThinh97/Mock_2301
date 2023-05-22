package com.vti.service.implement;

import com.vti.entity.Cart;
import com.vti.entity.Catalog;
import com.vti.form.creating.CartFormForCreating;
import com.vti.form.creating.CatalogFormForCreating;
import com.vti.form.updating.CartFormForUpdating;
import com.vti.form.updating.CatalogFormForUpdating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICartService {

	Page<Cart> getAllCarts(Pageable pageable);

	Page<Cart> getCartByUserId(Pageable pageable, int userId);

	void createCart(CartFormForCreating form);

	void updateQuantityInCart(int productId, int userId, CartFormForUpdating form);

	void deleteCartByUserId(int userId);

	void deleteProductInCartByProductId(int userId);

	int total(int userId);

}
