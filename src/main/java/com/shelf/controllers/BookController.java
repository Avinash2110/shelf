package com.shelf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shelf.payloads.BookDao;
import com.shelf.payloads.BookResponse;
import com.shelf.payloads.BooksResponse;
import com.shelf.payloads.GraphqlRequestBody;
import com.shelf.services.BookService;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private GraphQL graphql;

	@GetMapping("/books")
	public ResponseEntity<BooksResponse> getBooks() {
		BooksResponse booksResponse = bookService.fetchBooks();
		return new ResponseEntity<BooksResponse>(booksResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/book", consumes = "application/json")
	public ResponseEntity<BookResponse> createbook(@RequestBody BookDao bookDao) {
		BookResponse bookResponse = bookService.saveBook(bookDao);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.CREATED);
	}

	@GetMapping("/book/{id}")
	public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
		BookResponse bookResponse = bookService.fetchBookById(id);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ExecutionResult bookGraphqlEndpoint(@RequestBody GraphqlRequestBody body) {
		System.out.println(body.getQuery());
		System.out.println(body.getOperationName());
		if(body.getVariables()==null) {System.out.println(body.getVariables());}
		return graphql.execute(ExecutionInput.newExecutionInput().query(body.getQuery()).operationName(body.getOperationName())
				.variables(body.getVariables()).build());
	}

}
