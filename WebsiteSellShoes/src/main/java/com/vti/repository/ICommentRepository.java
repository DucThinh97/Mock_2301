package com.vti.repository;

import com.vti.entity.Catalog;
import com.vti.entity.Comment;
import com.vti.entity.OderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;

public interface ICommentRepository extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {

    Page<Comment> findByProductId(Pageable pageable, int productId);

    @Modifying
    void deleteCommentByUserIdAndProductId(int userId, int productId);

}
