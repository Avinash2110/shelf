package com.shelf.payloads;

import com.shelf.models.BookFormatType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookDao {
	
	private Long id;
	
	private String title;
	
	private String isbn;
	
	private String author;
	
	private boolean abridged;
	
	private BookFormatType bookFormat;
	
	private String publisher;
	
	private int noOfPages;
	
	private int price;

}
