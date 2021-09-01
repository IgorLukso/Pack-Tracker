package com.packtracker.site.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtracker.common.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/user/register")
	public String viewRegisterPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration_form";
	}
	
	@GetMapping("/user/edit")
	public String manageProfile(Model model) {
		
		Authentication aut = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = aut.getName();
		User user = service.getUserByEmail(currentPrincipalName);
		
		model.addAttribute("user", user);
		
		return "edit_form";
	}
	
	@PostMapping("/user/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		
		boolean newUser = (user.getId()==null);
		user.setEnabled(true);
		
		if (user.getPhone().isBlank()) user.setPhone(null);
		
		if (newUser) {
			redirectAttributes.addFlashAttribute("message", "Successfully registered!");
		} else
			redirectAttributes.addFlashAttribute("message", "Changes succesfully saved!");
		
		service.saveUser(user);
		
		if (newUser) {
			return "redirect:/login";
		}  else
			return "redirect:/user/edit";
	}
}
