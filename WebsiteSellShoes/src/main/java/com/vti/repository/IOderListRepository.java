package com.vti.repository;

import com.vti.entity.Cart;
import com.vti.entity.OderList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IOderListRepository extends JpaRepository<OderList, Integer>, JpaSpecificationExecutor<OderList> {

    Page<OderList> findByUserUsername(Pageable pageable, String username);

    Page<OderList> findByUserUsernameAndStatus(Pageable pageable, String username, OderList.Status status);



}
