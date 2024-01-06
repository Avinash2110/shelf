package com.shelf.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

/**
 * avinashshukla
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shelf.models.Book;
import com.shelf.payloads.BookDao;
import com.shelf.payloads.BookResponse;
import com.shelf.payloads.BooksResponse;
import com.shelf.repositories.BookRepository;

import graphql.schema.DataFetcher;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//api for inserting a new book by admin
	public BookResponse saveBook(BookDao bookRequest) {
		
		Book book = new Book();
		
		book.setAuthor(bookRequest.getAuthor());
		book.setAbridged(bookRequest.isAbridged());
		book.setBookFormat(bookRequest.getBookFormat());
		book.setIsbn(bookRequest.getIsbn());
		book.setNoOfPages(bookRequest.getNoOfPages());
		book.setPrice(bookRequest.getPrice());
		book.setPublisher(bookRequest.getPublisher());
		book.setTitle(bookRequest.getTitle());
		
		Book savedBook = bookRepository.save(book);
		
		BookDao bookDao = new BookDao();
		bookDao.setId(savedBook.getId());
		bookDao.setAbridged(savedBook.isAbridged());
		bookDao.setAuthor(savedBook.getAuthor());
		bookDao.setBookFormat(savedBook.getBookFormat());
		bookDao.setIsbn(savedBook.getIsbn());
		bookDao.setNoOfPages(savedBook.getNoOfPages());
		bookDao.setPrice(savedBook.getPrice());
		bookDao.setPublisher(savedBook.getPublisher());
		bookDao.setTitle(savedBook.getTitle());
		
		BookResponse bookResponse = new BookResponse();
		
		bookResponse.setBookDao(bookDao);
		bookResponse.setStatus("SUCCESS");
		bookResponse.setSuccessMessage("Book saved successfully");
		
		return bookResponse;
		
	}
	
	//API to fetch all the books available
	//TODO: In future we may need to create pagination as the number of books will be large
	public BooksResponse fetchBooks() {
		
		List<Book> books= bookRepository.findAll();
		List<BookDao> bookList= books.stream().map(entity -> modelMapper.map(entity, BookDao.class)).collect(Collectors.toList());
		
		BooksResponse booksResponse = new BooksResponse();
		booksResponse.setStatus("SUCCESS");
		booksResponse.setSuccessMessage("Retrieved Successfully");
		booksResponse.setBookList(bookList);
		
		return booksResponse;
	}
	
	public BookResponse fetchBookById(Long id) {
		Book book = bookRepository.getReferenceById(id);
		BookDao bookDao = modelMapper.map(book, BookDao.class);
		BookResponse bookResponse = new BookResponse();
		bookResponse.setBookDao(bookDao);
		bookResponse.setStatus("SUCCESS");
		bookResponse.setSuccessMessage("Book retrieved successfully");
		return bookResponse;
	}
	
	public DataFetcher<Book> graphqlFetchBookById() {
		return env -> {
			Long bookId = env.getArgument("id");
			return bookRepository.getReferenceById(bookId);
		};
	}
	
	public DataFetcher<List<Book>> graphqlFetchBooks(){
		return env -> {
			return bookRepository.findAll();
		};
	}

}
