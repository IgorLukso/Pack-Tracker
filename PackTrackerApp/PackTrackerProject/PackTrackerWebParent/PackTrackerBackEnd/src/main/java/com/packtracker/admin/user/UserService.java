package com.packtracker.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.packtracker.common.entity.User;

//this is a business (service) class 

@Service
public class UserService {

	@Autowired // injects an instance and implementation of UserRepository at runtime
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<User> listAll() {
		return (List<User>) repo.findAll();
	}
	
	public void saveUser(User user) {
		
		boolean newUser = (user.getId()==null);
		
		// encode password if it is a new user
		if (newUser) {
			encodePassword(user);
			
		// if in edit mode
		} else {
			
			// get existing user from database
			User existingUser = repo.findById(user.getId()).get();
			
			// if password field left empty set password to be the same as before
			if (user.getPassword().isEmpty()) {
				user.setPassword(existingUser.getPassword());
				
			// encode password if password field was edited	
			} else {
				encodePassword(user);
			}
		}
		repo.save(user);
	}
	
	public void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}
	
	public boolean isEmailUnique(Integer id, String email) {
		User userByEmail = repo.getUserByEmail(email);
		
		if (userByEmail == null) return true;
		
		boolean newUser = (id == null);
		
		// adding new user
		if (newUser) {
			if (userByEmail != null) return false;
		} else {
			
			// updating existing user
			if (userByEmail.getId() != id) {
				return false;
			}
		}
		return true; 
	}

	public User getUserById(Integer id) throws UserNotFoundException {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new UserNotFoundException("User with ID: "+id+" not found!");
		}
		
	}
	
	public void deleteUserById(Integer id) throws UserNotFoundException {
		
		Long countById = repo.countById(id);
		
		if (countById==null || countById==0) {
			throw new UserNotFoundException("User with ID: "+id+" not found!");
		}
		
		repo.deleteById(id);
	}
	
	
}
