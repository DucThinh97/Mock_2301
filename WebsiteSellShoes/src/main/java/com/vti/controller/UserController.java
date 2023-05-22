package com.vti.controller;

import com.vti.dto.ChangePublicAddrAndPhoneDTO;
import com.vti.dto.ChangePublicProfileDTO;
import com.vti.dto.ProfileDTO;
import com.vti.entity.User;
import com.vti.service.implement.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/users")
@Validated
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<?> existsUserByEmail(@PathVariable(name = "email") String email) {
        // get entity
        boolean result = userService.existsUserByEmail(email);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/userName/{userName}")
    public ResponseEntity<?> existsUserByUserName(@PathVariable(name = "userName") String userName) {
        // get entity
        boolean result = userService.existsUserByUserName(userName);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/activeUser")
    // validate: check exists, check not expired
    public ResponseEntity<?> activeUserViaEmail(@RequestParam String token) {
        // active user
        userService.activeUser(token);

        return new ResponseEntity<>("Active success!", HttpStatus.OK);
    }

    // resend confirm
    @GetMapping("/userRegistrationConfirmRequest")
    // validate: email exists, email not active
    public ResponseEntity<?> resendConfirmRegistrationViaEmail(@RequestParam String email) {

        userService.sendConfirmUserRegistrationViaEmail(email);

        return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
    }

    // reset password confirm
    @GetMapping("/resetPasswordRequest")
    // validate: email exists, email not active
    public ResponseEntity<?> sendResetPasswordViaEmail(@RequestParam String email) {

        userService.resetPasswordViaEmail(email);

        return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
    }

    // resend reset password
    @GetMapping("/resendResetPassword")
    // validate: email exists, email not active
    public ResponseEntity<?> resendResetPasswordViaEmail(@RequestParam String email) {

        userService.sendResetPasswordViaEmail(email);

        return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
    }

    @GetMapping("/resetPassword")
    // validate: check exists, check not expired
    public ResponseEntity<?> resetPasswordViaEmail(@RequestParam String token, @RequestParam String newPassword) {

        // reset password
        userService.resetPassword(token, newPassword);

        return new ResponseEntity<>("Reset Password success!", HttpStatus.OK);
    }

    @GetMapping("/profile")
    // validate: check exists, check not expired
    public ResponseEntity<?> getUserProfile() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        // get username from token
//		String username = authentication.getName();

        // get user info
        User user = userService.findUserByUserName(userDetails.getUsername());

        // convert user entity to user dto
        ProfileDTO profileDto = new ProfileDTO(
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().getERole(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getStatus().toString()
        );

        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    // in profile
    @PutMapping("/fullProfile")
    // validate: check exists, check not expired
    public ResponseEntity<?> changeUserProfile(@RequestBody ChangePublicProfileDTO dto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

//        get username from token
//        String username = authentication.getName();

        userService.changeUserProfile(userDetails.getUsername(), dto);

        return new ResponseEntity<>("Change Profile Successfully!", HttpStatus.OK);
    }

    // in payment
    @PutMapping("/paymentProfile")
    // validate: check exists, check not expired
    public ResponseEntity<?> changeAddrAndPhone(@RequestBody ChangePublicAddrAndPhoneDTO dto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        userService.changeAddrAndPhone(userDetails.getUsername(), dto);

        return new ResponseEntity<>("Change Profile Successfully!", HttpStatus.OK);
    }

}
