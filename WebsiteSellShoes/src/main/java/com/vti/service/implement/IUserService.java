package com.vti.service.implement;

import com.vti.dto.ChangePublicAddrAndPhoneDTO;
import com.vti.entity.Product;
import com.vti.form.creating.ProductFormForCreating;
import com.vti.form.filter.ProductFilter;
import com.vti.form.updating.ProductFormForUpdating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vti.dto.ChangePublicProfileDTO;
import com.vti.entity.User;

import java.util.List;

public interface IUserService extends UserDetailsService {

//	Page<Product> getAllUsers(Pageable pageable, ProductFilter filter, String search);
//
//	Product getUserByID(int id);
//
//	void createUser(ProductFormForCreating form);
//
//	void updateUser(int id, ProductFormForUpdating form);
//
//	void deleteUser(List<Integer> ids);
//
//	void createUser(User user);

    User findUserByEmail(String email);

    User findUserByUserName(String username);

    void activeUser(String token);

    void sendConfirmUserRegistrationViaEmail(String email);

    boolean existsUserByEmail(String email);

    boolean existsUserByUserName(String userName);

    void resetPasswordViaEmail(String email);

    void resetPassword(String token, String newPassword);

    void sendResetPasswordViaEmail(String email);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void changeUserProfile(String username, ChangePublicProfileDTO dto);

    void changeAddrAndPhone(String username, ChangePublicAddrAndPhoneDTO dto);
}
