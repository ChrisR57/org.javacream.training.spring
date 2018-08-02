package org.javacream.books.warehouse.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.javacream.books.warehouse.api.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JpaUtil {
	@PersistenceContext private EntityManager entityManager;
	
	@Transactional
	public void bookUtil(String isbn) {
		Book searched = entityManager.find(Book.class, isbn);
		//System.out.println("********** " + (book == searched));
		searched.setTitle("CHANGED");
	}

}
