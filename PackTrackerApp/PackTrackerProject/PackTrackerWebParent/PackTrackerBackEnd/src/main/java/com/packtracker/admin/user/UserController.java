package com.packtracker.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtracker.common.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	//handler methods for http requests
	
	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> listOfUsers = service.listAll();
		model.addAttribute("listOfUsers", listOfUsers);
		return "users";
	}
	
	@GetMapping("/users/new")
	public String createNewUser(Model model) {
		
		User user = new User();
		user.setEnabled(true); // sets enabled option to "true" in the user form by default.
		
		model.addAttribute("user", user);
		model.addAttribute("pageTitle", "Add New User");
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		
		if (user.getPhone().isBlank()) user.setPhone(null);
		service.saveUser(user);
		
		// "message" added temporarily to the model before redirect and then removed after use.
		redirectAttributes.addFlashAttribute("message","User successfully saved!");
		
		return "redirect:/users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable(name="id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		try {
			
			User user = service.getUserById(id);
			model.addAttribute("user", user);
			model.addAttribute("pageTitle", "Edit user with ID: "+id);
			return "user_form";
			
		} catch (UserNotFoundException e) {
			
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/users";
		}
	}
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable(name="id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		
		try {
			service.deleteUserById(id);
			redirectAttributes.addFlashAttribute("message","User with ID: "+id+" successfully deleted!");
			return "redirect:/users";
		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/users";
		}
	}
	
	
	
}
