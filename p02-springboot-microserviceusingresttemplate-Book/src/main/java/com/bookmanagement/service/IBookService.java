package com.bookmanagement.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.bookmanagement.model.BookEntity;
import com.bookmanagement.model.BookSearchCriteria;
import com.bookmanagement.utility.BookNotFoundException;

public interface IBookService {

	BookEntity save(@Valid BookEntity book);

	List<BookEntity> getAll();

	BookEntity getBookById(Long id) throws BookNotFoundException;

	BookEntity updateBookById(Long id, BookEntity book) throws BookNotFoundException;

	ResponseEntity<String> deleteBookById(Long id) throws BookNotFoundException;

	List<BookEntity> getBookByPrice(Double price);

	List<BookEntity> getBookByPriceGreaterThan(Double price);
	
	public List<BookEntity> getQueryResult(List<BookSearchCriteria> criteriaFilter);

}
