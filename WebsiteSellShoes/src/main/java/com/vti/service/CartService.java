package com.vti.service;

import com.vti.entity.Cart;
import com.vti.entity.Pay;
import com.vti.form.creating.CartFormForCreating;
import com.vti.form.creating.PayFormForCreating;
import com.vti.form.updating.CartFormForUpdating;
import com.vti.repository.ICartRepository;
import com.vti.repository.IPayRepository;
import com.vti.service.implement.ICartService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService implements ICartService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IPayRepository payRepository;


    @Override
    public Page<Cart> getAllCarts(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

    @Override
    public Page<Cart> getCartByUserId(Pageable pageable, int userId) {
        return cartRepository.findAllByUserId(pageable, userId);
    }

    @Override
    public void createCart(CartFormForCreating form) {
        // omit id field
        Cart.ShoppingCartKey shoppingCartKey = modelMapper.map(form, Cart.ShoppingCartKey.class);
        TypeMap<CartFormForCreating, Cart> typeMap = modelMapper.getTypeMap(CartFormForCreating.class, Cart.class);
        if (typeMap == null) { // if not already added
            // skip field
            modelMapper.addMappings(new PropertyMap<CartFormForCreating, Cart>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        // convert form to entity
        Cart cart = modelMapper.map(form, Cart.class);
        cart.setId(shoppingCartKey);
        cart.setQuantity(1);
        cartRepository.save(cart);
    }

    @Override
    public void updateQuantityInCart(int productId, int userId, CartFormForUpdating form) {
        Cart entity = cartRepository.findProductByProductIdAndUserId(productId, userId);
        entity.setQuantity(form.getQuantity());
        cartRepository.save(entity);
    }


    @Override
    public void deleteCartByUserId(int userId) {
        cartRepository.deleteCartByUserId(userId);
    }

    @Override
    public void deleteProductInCartByProductId(int productId) {
        cartRepository.deleteProductInCartByProductId(productId);
    }

    @Override
    public int total(int userId) {
        PayFormForCreating form = new PayFormForCreating();
        int sum = cartRepository.total(userId);
        form.setUserId(userId);
        form.setTotal(sum);
        Pay pay = modelMapper.map(form, Pay.class);
        payRepository.save(pay);
        return sum;
    }


}
