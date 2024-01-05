package com.shelf.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BooksResponse {
	
	private List<BookDao> bookList;
	
	private String status;
	
	private String successMessage;

}
