package com.vti.dto.SiginAndSignup;

import com.vti.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {


    // check not null, check length, check exists, check format (regex)...
    private String username;

    // check not null, check length, check exists on database, check format
    // (regex)...
    private String email;

    // check not null, check length, check format (regex)...
    private String password;

    // check not null, check length, check format (regex)...
    private String firstName;

    // check not null, check length, check format (regex)...
    private String lastName;

    private List<String> role;

    public User toEntity() {
        return new User(username, email, password);
    }

}
