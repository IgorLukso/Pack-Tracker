package com.packtracker.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.packtracker.common.entity.Pet;
import com.packtracker.common.entity.User;
import com.packtracker.site.pet.PetService;
import com.packtracker.site.user.UserService;

@Controller
public class MainController {
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private UserService userService;

	//handles http GET request
	@GetMapping("/")
	public String viewHomePage(@Param("keyword") String keyword, Model model) {
		
		Authentication aut = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = aut.getName();
		User currentUser = userService.getUserByEmail(currentPrincipalName);
		
		List<Pet> listOfMissing = petService.listMissingByAreaAndKeyword(currentUser.getLocation(), keyword);
		model.addAttribute("listOfMissing", listOfMissing);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("keyword",keyword);
		
		return "index";
	}
			
	@GetMapping("/login")
	public String viewLoginPage() {
		return "login";
	}
	
}