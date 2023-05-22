package com.vti.form.creating;

import com.vti.entity.Catalog;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentFormForCreating {

	private int userId;

	private int productId;

	private String content;

}
