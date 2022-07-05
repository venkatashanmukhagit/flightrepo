package com.bookmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.model.BookEntity;
import com.bookmanagement.model.BookSearchCriteria;
import com.bookmanagement.model.QueryOperatorEnum;
import com.bookmanagement.service.IBookService;
import com.bookmanagement.utility.BookNotFoundException;

@RestController
public class BookController {

	@Autowired
	IBookService bookService;

	@PostMapping(path = "/book")
	public BookEntity addBook(@Valid @RequestBody BookEntity book) {
		return bookService.save(book);

	}

	@GetMapping(path = "/book")
	public List<BookEntity> getAllBooks() {
		return bookService.getAll();

	}

	@GetMapping(path = "/book/{id}")
	public BookEntity getBookById(@PathVariable Long id) throws BookNotFoundException {
		return bookService.getBookById(id);

	}

	@PutMapping(path = "/book/{id}")
	public BookEntity updateBookById(@PathVariable Long id, @RequestBody BookEntity book) throws BookNotFoundException {
		return bookService.updateBookById(id, book);

	}

	@DeleteMapping(path = "/book/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable Long id) throws BookNotFoundException {
		return bookService.deleteBookById(id);

	}

	@GetMapping(path = "/book/byprice/{price}")
	public List<BookEntity> getBookByPrice(@PathVariable Double price) {
		return bookService.getBookByPrice(price);

	}

	@GetMapping(path = "/book/bypriceGreaterThan/{price}")
	public List<BookEntity> getBookByPriceGreaterThan(@PathVariable Double price) {
		return bookService.getBookByPriceGreaterThan(price);

	}

	@GetMapping(path = "/book/findAll")
	public List<BookEntity> getBook(@RequestParam(value = "search") String search) {
		List<BookSearchCriteria> criteriaFilterList = new ArrayList<>();
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			criteriaFilterList.add(BookSearchCriteria.builder().field(matcher.group(1))
					.operator(QueryOperatorEnum.fromSymbol(matcher.group(2))).value(matcher.group(3)).build());
		}
		return bookService.getQueryResult(criteriaFilterList);

	}

}
