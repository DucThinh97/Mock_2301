package com.vti.form.creating;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OderDetailFormForCreating implements Serializable {

    private int oderListId;

    private String productName;

    private int price;

    private int quantity;

    private int total;


}
