package org.javacream.training.store.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreRestService {

	@RequestMapping(method=RequestMethod.GET, path="store/{cat}/{item}")
	public int getStock(@PathVariable("cat") String category, @PathVariable("item")String item) {
		System.out.println("Cat:" + category + ", item" + item);
		return 4711;
	}
}
