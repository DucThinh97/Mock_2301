package com.vti.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.OderList;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OderListDTO {

    @NonNull
    private int id;

    @NonNull
    private int totalPayment;

    @NonNull
    private String status;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime oderDate;


}
