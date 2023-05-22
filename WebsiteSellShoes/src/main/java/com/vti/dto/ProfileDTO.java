package com.vti.dto;

import com.vti.entity.Role;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {

    private String userName;

    private String email;

    private String firstName;

    private String lastName;

    private Role.ERole role;

    private String phoneNumber;

    private String address;

    private String status;



}
