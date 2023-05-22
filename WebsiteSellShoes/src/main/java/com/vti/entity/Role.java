package com.vti.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`Role`")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "roleId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Enumerated(EnumType.STRING)
    @Column(name = "roleName", unique = true)
    private ERole eRole = ERole.USER;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public enum ERole {
        ADMIN, STAFF, USER;
    }

}
