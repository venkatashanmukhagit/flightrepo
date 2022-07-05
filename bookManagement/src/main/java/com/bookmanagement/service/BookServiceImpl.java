package com.bookmanagement.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bookmanagement.model.BookEntity;
import com.bookmanagement.model.BookSearchCriteria;
import com.bookmanagement.repository.IBookRepository;
import com.bookmanagement.specification.BookSpecification;
import com.bookmanagement.utility.BookNotFoundException;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	IBookRepository bookRepository;
	
	@Autowired
	BookSpecification bookSpecification;

	@Override
	public BookEntity save(@Valid BookEntity book) {
		return bookRepository.save(book);
	}

	@Override
	public List<BookEntity> getAll() {
		return bookRepository.findAll();
	}

	@Override
	public BookEntity getBookById(Long id) throws BookNotFoundException {
		return bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found for id : " + id));
	}

	@Override
	public BookEntity updateBookById(Long id, BookEntity book) throws BookNotFoundException {
		Optional<BookEntity> op = bookRepository.findById(id);
		if (op.isPresent()) {
			BookEntity fetchedBook = op.get();
			if (!ObjectUtils.isEmpty(book.getAuthor())) {
				fetchedBook.setAuthor(book.getAuthor());
			}
			if (!ObjectUtils.isEmpty(book.getTitle())) {
				fetchedBook.setTitle(book.getTitle());
			}
			if (!ObjectUtils.isEmpty(book.getPrice())) {
				fetchedBook.setPrice(book.getPrice());
			}
			return bookRepository.save(fetchedBook);

		} else {
			throw new BookNotFoundException("Book not found for id : " + id);
		}
	}

	@Override
	public ResponseEntity<String> deleteBookById(Long id) throws BookNotFoundException {
		BookEntity book = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found for id : " + id));
		bookRepository.delete(book);
		return ResponseEntity.ok("BookId with id " + id + " deleted successfully.");

	}

	@Override
	public List<BookEntity> getBookByPrice(Double price) {
		return bookRepository.findByPrice(price);
	}

	@Override
	public List<BookEntity> getBookByPriceGreaterThan(Double price) {
		return bookRepository.findByPriceGreaterThan(price);
	}
	
	@Override
	public List<BookEntity> getQueryResult(List<BookSearchCriteria> criteriaFilter) {
		if(criteriaFilter.isEmpty()) {
			return bookRepository.findAll();
		}else {
			return bookRepository.findAll(bookSpecification.getSpecificationFromFilters(criteriaFilter));
		}

	}


}
