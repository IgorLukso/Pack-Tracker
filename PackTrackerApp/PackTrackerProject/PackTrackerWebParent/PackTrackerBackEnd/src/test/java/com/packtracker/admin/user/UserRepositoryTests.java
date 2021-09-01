package com.packtracker.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.packtracker.common.entity.User;


@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE) //run the test on the real database
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	
	@Test
	public void testCreateUser() {
		
		User fero = new User("fero@gmail.com","fero123","D6W","0875434323");
		User savedUser = repo.save(fero);
					
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		
		Iterable<User> usersList = repo.findAll();
		usersList.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		
		User user = repo.findById(1).get();
		System.out.println(user);
		assertThat(user).isNotNull();
	}
	
	@Test 
	public void testUpdateUserDetails() {
		
		User user = repo.findById(22).get();
		user.setPassword("hrushka123");
		user.setEmail("hrushka@gmail.com");
		
		System.out.println(user);
	}
	
	@Test
	public void testDeleteUserById() {
		
		repo.deleteById(22);
		
		Iterable<User> usersList = repo.findAll();
		usersList.forEach(user -> System.out.println(user));
		
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "robert@gmail.com";
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
		
		System.out.println(user);
	}
	
	@Test
	public void testCountById() {
		Integer id = 5;
		Long countById = repo.countById(id);
		assertThat(countById).isNotNull().isGreaterThan(0);
		
		System.out.println("Number of users with ID "+id+": "+countById);
		
	}
	
}
