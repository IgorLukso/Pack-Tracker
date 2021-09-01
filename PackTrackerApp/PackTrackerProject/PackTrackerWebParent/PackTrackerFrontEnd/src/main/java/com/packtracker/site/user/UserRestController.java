package com.packtracker.site.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/user/check_email")
	public String checkDuplicateEmail(@Param("email") String email) {
		
		return service.isEmailUnique(email)? "unique":"duplicate";
	}
}