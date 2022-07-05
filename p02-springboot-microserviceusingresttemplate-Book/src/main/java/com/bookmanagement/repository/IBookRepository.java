package com.bookmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bookmanagement.model.BookEntity;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {

	List<BookEntity> findByPrice(Double price);

	List<BookEntity> findByPriceGreaterThan(Double price);

}
