package com.vti.controller;


import com.vti.dto.SiginAndSignup.LoginRequest;
import com.vti.dto.SiginAndSignup.SignupRequest;
import com.vti.entity.Role;
import com.vti.entity.User;
import com.vti.entity.UserStatus;
import com.vti.entity.token.RegistrationUserToken;
import com.vti.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.vti.repository.IUserRepository;
import com.vti.repository.RegistrationUserTokenRepository;
import com.vti.repository.ResetPasswordTokenRepository;
import com.vti.repository.RoleRepository;
import com.vti.response.MessageResponse;
import com.vti.response.UserInfoResponse;
import com.vti.security.jwt.JwtUtils;
import com.vti.service.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private RegistrationUserTokenRepository registrationUserTokenRepository;

    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        UserDetail userDetails;
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            userDetails = (UserDetail) authentication.getPrincipal();

        } catch (Exception ex) {
            throw new Exception(ex);
        }
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        String jwt = jwtUtils.generateJwtToken(authentication);

        List<String> role = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(
//                        (userDetails.getStatus() == UserStatus.ACTIVE) ? jwt : null
                        userDetails.getStatus().equals(UserStatus.ACTIVE) ? jwt : null
                        , userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
                        role.toString(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getStatus().toString()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getFirstName(), signUpRequest.getLastName());


        if (user.getRole() == null) {
            Role role = new Role();
            role.setId((short) 3);
            role.setERole(Role.ERole.USER);
            user.setRole(role);
        }

        userRepository.save(user);

        // create new user registration token
        createNewRegistrationUserToken(user);

        // send email to confirm
        sendConfirmUserRegistrationViaEmail(user.getEmail());

        return ResponseEntity.ok(new MessageResponse("We have sent an email. Please check email to active account!"));


    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.clearJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    private void createNewRegistrationUserToken(User user) {

        // create new token for confirm Registration
        final String newToken = UUID.randomUUID().toString();
        RegistrationUserToken token = new RegistrationUserToken(newToken, user);

        registrationUserTokenRepository.save(token);
    }

    public void sendConfirmUserRegistrationViaEmail(String email) {
        eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
    }
}
