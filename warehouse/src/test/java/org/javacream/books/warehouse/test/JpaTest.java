package org.javacream.books.warehouse.test;

import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.config.ApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ApplicationConfiguration.class)
public class JpaTest {

	@Autowired private BooksService booksService;
	
	@Test public void doTest() throws BookException {
		String isbn = booksService.newBook("EGAL");
		booksService.jpaDemo(isbn);

	}
}
