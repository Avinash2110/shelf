package com.shelf.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookResponse {
	
	private BookDao bookDao;
	
	private String status;
	
	private String successMessage;

}
