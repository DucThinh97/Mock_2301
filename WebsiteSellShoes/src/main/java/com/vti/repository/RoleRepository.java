package com.vti.repository;


import com.vti.entity.ERole;
import com.vti.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
//    Optional<Role> findByERole(ERole name);
}
