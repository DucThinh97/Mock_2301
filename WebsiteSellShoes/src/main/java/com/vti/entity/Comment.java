package com.vti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`Comment`")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @Column(name = "content")
    private String content;

    @CreatedDate
    @Column(name = "createDate", insertable = false)
    private LocalDateTime createDate;

}