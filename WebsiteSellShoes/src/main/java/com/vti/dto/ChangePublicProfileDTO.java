package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ChangePublicProfileDTO {


    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String address;

    @NonNull
    private String phoneNumber;




}
