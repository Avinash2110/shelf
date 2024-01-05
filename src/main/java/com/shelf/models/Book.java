package com.shelf.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="title", nullable = false)
	private String title;
	
	@Column(name = "isbn", nullable = false, unique = true)
	private String isbn;
	
	@Column(name="author", nullable = false)
	private String author;
	
	@Column(name="isAbridged")
	private boolean abridged;
	
	@Column(name="bookFormat")
	private BookFormatType bookFormat;
	
	@Column(name="publisher")
	private String publisher;
	
	@Column(name="noOfPages")
	private int noOfPages;
	
	@Column(name="price")
	private int price;

}
