package org.javacream.books.warehouse.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.javacream.books.isbngenerator.api.IsbnGeneratorService;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainer.sawitzki@javacream.org
 * 
 */

@Repository
public class JpaBooksService implements BooksService {

	@Autowired JpaUtil jpaUtil;
	@Autowired
	@Qualifier(IsbnGeneratorService.Algorithms.SEQUENCE)
	private IsbnGeneratorService isbnGenerator;

	@PersistenceContext
	private EntityManager entityManager;//Dieser entityManager aus Sicht der Programmierung KEIN SINGLETON

	@Autowired
	private StoreService storeService;

	@Transactional
	public String newBook(String title) throws BookException {
		String isbn = isbnGenerator.next();
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		entityManager.persist(book);
		return isbn;
	}

	@Transactional
	public Book findBookByIsbn(String isbn) throws BookException {
		try {
			Book result = entityManager.find(Book.class, isbn);
			result.setAvailable(storeService.getStock("books", isbn) > 0);
			return result;
		} catch (RuntimeException e) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
	}
	@Transactional
	public Book updateBook(Book bookValue) throws BookException {
		entityManager.merge(bookValue);
		return bookValue;
	}

	@Transactional (rollbackFor=BookException.class) public void deleteBookByIsbn(String isbn) throws BookException {
		try {
			//Book bookComplete = entityManager.find(Book.class, isbn);
			Book bookProxy = entityManager.getReference(Book.class, isbn);
			entityManager.remove(bookProxy);
		} catch (RuntimeException e) {
			throw new BookException(BookException.BookExceptionType.NOT_DELETED, isbn);
		}
	}
	@Transactional
	public Collection<Book> findAllBooks() {
		return entityManager.createQuery("select b from BookEntity as b").getResultList();
	}

	@Transactional
	public void jpaDemo(String isbn) {
		try {
			Book book = findBookByIsbn(isbn);
			entityManager.detach(book);
			jpaUtil.bookUtil(isbn);
			System.out.println("************ " + book.getTitle());
			Book merged = entityManager.merge(book);
			merged.setPrice(999.99);
		} catch (BookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
