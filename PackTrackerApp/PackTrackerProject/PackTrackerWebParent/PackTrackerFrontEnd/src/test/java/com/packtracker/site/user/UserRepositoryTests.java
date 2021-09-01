package com.packtracker.site.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.packtracker.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void getUserByEmailTest() {
		String email = "john@nomail.com";
		User user = userRepo.getUserByEmail(email);
		
		System.out.println(user);
		
		assertThat(user).isNotNull();
	}
	
	@Test
	public void getUsersByLocationTest() {
		String location = "D2";
		
		List<User> list = userRepo.getUsersByLocation(location);
		
		list.forEach(user -> System.out.println(user));
		
		assertThat(list).isNotEmpty();
	}
	
}
