package com.vti.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vti.entity.Role;
import com.vti.entity.User;
import com.vti.entity.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class UserDetail implements UserDetails {

    private static final long serialVersionUID = 1L;

    private int id;

    private String userName;

    private String email;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    private String role;

    private UserStatus status;
    private Collection<? extends GrantedAuthority> authorities;


    public UserDetail(int id, String username, String email, String password, String firstName, String lastName, UserStatus status, Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.userName = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.authorities = authorities;
    }

    public static UserDetail build(User user) {
        List<Role> listRole = new ArrayList<>();
        listRole.add(user.getRole());
        List<GrantedAuthority> authorities = listRole.stream()
                .map(role -> new SimpleGrantedAuthority(role.getERole().name())).collect(Collectors.toList());
        return new UserDetail(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),user.getFirstName(), user.getLastName(), user.getStatus(), authorities);
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetail user = (UserDetail) o;
        return Objects.equals(id, user.id);
    }
}
