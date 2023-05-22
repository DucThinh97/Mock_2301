package com.vti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CommentDTO {

    @NonNull
    private String productId;

    @NonNull
    @JsonProperty("commentId")
    private int id;

    @NonNull
    private String userId;

    @NonNull
    private String userUsername;

    @NonNull
    private String content;

    @NonNull
    private LocalDateTime createDate;


}
