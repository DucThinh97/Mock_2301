package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ChangePublicAddrAndPhoneDTO {


    @NonNull
    private String address;

    @NonNull
    private String phoneNumber;


}
