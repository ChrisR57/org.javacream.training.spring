package org.javacream.training.store.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoreWebController {
	@GetMapping("/stock")
	public String getStock(@RequestParam(name = "category", required = true) String category,
			@RequestParam(name = "item", required = true) String item, Model model) {
		System.out.println("Cat:" + category + ", item " + item);
		model.addAttribute("retrievedStock", 9);
		return "stockResult";
	}

}
