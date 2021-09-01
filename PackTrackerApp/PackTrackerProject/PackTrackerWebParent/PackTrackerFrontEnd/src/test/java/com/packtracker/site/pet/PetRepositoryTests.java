package com.packtracker.site.pet;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.packtracker.common.entity.Pet;
import com.packtracker.common.entity.User;
import com.packtracker.site.user.UserRepository;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class PetRepositoryTests {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PetRepository petRepo;
	
	
	@Test
	public void findAllByUserTest() {
		User currentUser = userRepo.getUserByEmail("tais@hotmail.com");
		List<Pet> listOfPetsByUser = petRepo.getAllbyUser(currentUser);
		
		listOfPetsByUser.forEach(pet -> System.out.println(pet));
		
		assertThat(listOfPetsByUser).isNotEmpty();
	}
	
	@Test
	public void countByIdTest() { 
		int id = 1;
		Long count = petRepo.countById(1);
		assertThat(count).isGreaterThan(0);
		
		System.out.println("Number of pets with ID "+id+": "+count);
	}
	
	// listing missing pets by area
	@Test
	public void findMissingByAreaTest() {
		
		String area = "D1";
		
		List<Pet> listOfMissing = petRepo.getMissingByArea(area);
		
		System.out.println();
		listOfMissing.forEach(pet -> System.out.println(pet));
				
		assertThat(listOfMissing.size()).isGreaterThan(0);
	}
	
	@Test
	public void findMissingByAreaAndKeyword() {
		
		String keyword = "cat";
		String area = "D1";
		List<Pet> list = petRepo.getMissingByAreaAndKeyword(area, keyword);
		
		System.out.println();
		list.forEach(pet -> System.out.println(pet));
		System.out.println();
		
		assertThat(list.size()).isGreaterThan(0);
	}
	
	
}
