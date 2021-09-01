package com.packtracker.site.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.packtracker.common.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
	
	// custom query to retrieve user by email
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);
	
	public Long countById(Integer id);
	
	@Query("SELECT u FROM User u WHERE u.location = :location")
	public List<User> getUsersByLocation(@Param("location") String location);
		
}