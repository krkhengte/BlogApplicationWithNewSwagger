package jfs.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@GetMapping("/")
	public String welcomeMsg() {
		
		return "Welcome To Swagger 3";
	}
	
}
