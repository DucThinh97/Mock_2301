package com.vti.form.creating;

import com.vti.entity.OderList;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OderListFormForCreating {

    private int userId;

    private Integer totalPayment;

    private OderList.Status status;


}
