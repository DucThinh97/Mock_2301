package com.vti.service;

import java.util.List;
import java.util.UUID;

import com.vti.dto.ChangePublicAddrAndPhoneDTO;
import com.vti.entity.*;
import com.vti.form.creating.ProductFormForCreating;
import com.vti.form.filter.ProductFilter;
import com.vti.form.updating.ProductFormForUpdating;
import com.vti.service.implement.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vti.dto.ChangePublicProfileDTO;
import com.vti.entity.token.RegistrationUserToken;
import com.vti.entity.token.ResetPasswordToken;
import com.vti.event.OnResetPasswordViaEmailEvent;
import com.vti.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.vti.repository.RegistrationUserTokenRepository;
import com.vti.repository.ResetPasswordTokenRepository;
import com.vti.repository.IUserRepository;

@Component
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserRepository IUserRepository;

    @Autowired
    private RegistrationUserTokenRepository registrationUserTokenRepository;

    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public Page<Product> getAllUsers(Pageable pageable, ProductFilter filter, String search) {
//        return null;
//    }
//
//    @Override
//    public Product getUserByID(int id) {
//        return null;
//    }
//
//    @Override
//    public void createUser(ProductFormForCreating form) {
//
//    }
//
//    @Override
//    public void updateUser(int id, ProductFormForUpdating form) {
//
//    }
//
//    @Override
//    public void deleteUser(List<Integer> ids) {
//
//    }
//
//    @Override
//    public void createUser(User user) {
//
//        // encode password
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        // create user
//        if (user.getRole() == null) {
//            Role role = new Role();
//            role.setId((short) 3);
//            role.setERole(Role.ERole.USER);
//            user.setRole(role);
//        }
//        IUserRepository.save(user);
//
//        // create new user registration token
//        createNewRegistrationUserToken(user);
//
//        // send email to confirm
//        sendConfirmUserRegistrationViaEmail(user.getEmail());
//    }

    private void createNewRegistrationUserToken(User user) {

        // create new token for confirm Registration
        final String newToken = UUID.randomUUID().toString();
        RegistrationUserToken token = new RegistrationUserToken(newToken, user);

        registrationUserTokenRepository.save(token);
    }

    @Override
    public void sendConfirmUserRegistrationViaEmail(String email) {
        eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
    }

    @Override
    public User findUserByEmail(String email) {
        return IUserRepository.findByEmail(email);
    }

    @Override
    public User findUserByUserName(String username) {
        return IUserRepository.findByUsername(username);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return IUserRepository.existsByEmail(email);
    }

    @Override
    public boolean existsUserByUserName(String userName) {
        return IUserRepository.existsByUsername(userName);
    }

    @Override
    public void activeUser(String token) {

        // get token
        RegistrationUserToken registrationUserToken = registrationUserTokenRepository.findByToken(token);

        // active user
        User user = registrationUserToken.getUser();
        user.setStatus(UserStatus.ACTIVE);
        IUserRepository.save(user);

        // remove Registration User Token
        registrationUserTokenRepository.deleteById(registrationUserToken.getId());
    }

    @Override
    public void resetPasswordViaEmail(String email) {

        // find user by email
        User user = findUserByEmail(email);

        // remove token token if exists
        resetPasswordTokenRepository.deleteByUserId(user.getId());

        // create new reset password token
        createNewResetPasswordToken(user);

        // send email
        sendResetPasswordViaEmail(email);
    }

    @Override
    public void sendResetPasswordViaEmail(String email) {
        eventPublisher.publishEvent(new OnResetPasswordViaEmailEvent(email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = IUserRepository.findByUsername(username);

        return UserDetail.build(user);
    }

    private void createNewResetPasswordToken(User user) {

        // create new token for Reseting password
        final String newToken = UUID.randomUUID().toString();
        ResetPasswordToken token = new ResetPasswordToken(newToken, user);

        resetPasswordTokenRepository.save(token);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        // get token
        ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(token);

        // change password
        User user = resetPasswordToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        IUserRepository.save(user);

        // remove Reset Password
        resetPasswordTokenRepository.deleteById(resetPasswordToken.getId());
    }


	@Override
	public void changeUserProfile(String username, ChangePublicProfileDTO dto) {
		User user = IUserRepository.findByUsername(username);

		user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAddress(dto.getAddress());
        user.setPhoneNumber(dto.getPhoneNumber());
        IUserRepository.save(user);

		// TODO other field
	}

    @Override
    public void changeAddrAndPhone(String username, ChangePublicAddrAndPhoneDTO dto) {
        User user = IUserRepository.findByUsername(username);

        user.setAddress(dto.getAddress());
        user.setPhoneNumber(dto.getPhoneNumber());
        IUserRepository.save(user);

        // TODO other field
    }

}
