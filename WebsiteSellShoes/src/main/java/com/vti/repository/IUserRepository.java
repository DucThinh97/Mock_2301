package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	 boolean existsByUsername(String userName);

	 boolean existsByEmail(String email);
//
//	@Query("	SELECT 	status 		"
//			+ "	FROM 	User 		"
//			+ " WHERE 	email = :email")
//	public UserStatus findStatusByEmail(@Param("email") String email);
//
	 User findByUsername(String name);

	 User findByEmail(String email);
}
