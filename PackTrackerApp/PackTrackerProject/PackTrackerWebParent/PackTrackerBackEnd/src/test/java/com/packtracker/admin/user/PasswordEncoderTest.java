package com.packtracker.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
	
	@Test
	public void testEncodePassword() {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "igor123";
		String encodedPassword = passwordEncoder.encode(rawPassword);
		
		System.out.println("Raw password: "+rawPassword);
		System.out.println("Encoded password: "+encodedPassword);
		
		boolean match = passwordEncoder.matches(rawPassword, encodedPassword);
		
		System.out.println("Match: "+match);
		
		assertThat(match).isTrue();
		
	}
}
