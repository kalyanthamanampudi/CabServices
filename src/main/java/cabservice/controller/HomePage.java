package cabservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class HomePage {

	
	@GetMapping("/")
	public String getHomePage() {
		return "index";
	}
}
