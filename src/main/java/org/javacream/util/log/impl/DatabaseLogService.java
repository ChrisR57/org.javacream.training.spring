package org.javacream.util.log.impl;

import org.javacream.util.log.api.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatabaseLogService implements LogService {
	
	@Autowired private JdbcTemplate jdbcTemplate;
	@Override
	//@Transactional //1 + 2
	@Transactional(propagation=Propagation.REQUIRES_NEW) //3
	public void log(String message) {
		jdbcTemplate.execute("insert into LOG values('" + message + "')");
		//throw new RuntimeException("TEST ROLLBACK"); //1, 3
	}

}
