package com.vti.repository;

import com.vti.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;

public interface IPayRepository extends JpaRepository<Pay, Integer>, JpaSpecificationExecutor<Pay> {

    @Modifying
    void deleteByUserId(int userId);
}
