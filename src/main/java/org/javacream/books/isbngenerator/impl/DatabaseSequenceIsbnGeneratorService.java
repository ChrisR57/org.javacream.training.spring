package org.javacream.books.isbngenerator.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.javacream.books.isbngenerator.api.IsbnGeneratorService;
import org.javacream.util.log.api.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier(IsbnGeneratorService.Algorithms.SEQUENCE)
public class DatabaseSequenceIsbnGeneratorService implements IsbnGeneratorService {

	@Value("${isbngenerator.prefix}")
	private String prefix;
	@Value("${isbngenerator.countryCode}")
	private String countryCode;

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private LogService logService;
	@Autowired
	private DatabaseSequenceIsbnGeneratorService delegate;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String suffix) {
		this.countryCode = suffix;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String next() {
		int nextKey = delegate.nextKey();
		String isbn = prefix + nextKey + countryCode;
		try { //2 + 1 -> Exception, transaction marked for rollback, 3 + 3 -> key updated, log not written
			logService.log("created new isbn " + isbn + " at " + new Date());
		} catch (RuntimeException e) {
			//OK
		}
//		logService.log("created new isbn " + isbn + " at " + new Date());
		return isbn;

	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int nextKey() {
		Integer key = (Integer) entityManager.createNativeQuery("select col_key from keys").getSingleResult();
		key++;
		Query query = entityManager.createNativeQuery("update keys set col_key= :key");
		query.setParameter("key", key);
		query.executeUpdate();
		return key;
	}
}
