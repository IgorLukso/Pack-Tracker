package com.packtracker.common.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="User")
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=128, nullable=false, unique=true)
	private String email;
	
	@Column(length=64, nullable=false)
	private String password;
	
	@Column(length=64, nullable=false)
	private String location;
	
	@Column(length=15, nullable=true)
	private String phone;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Pet> pets = new HashSet<>();
	
	private boolean enabled;
	
	private boolean admin;
	
	public Set<Pet> getPets() {
		return pets;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public User() {
	}

	public User(String email, String password, String location, String phone) {
		this.email = email;
		this.password = password;
		this.location = location;
		this.phone = phone;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void addPet(Pet pet) {
		pets.add(pet);
		pet.setUser(this);
	}
	
	public void removePet(Pet pet) {
		pets.remove(pet);
		pet.setUser(null);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", location=" + location + ", pets=" + pets + ", enabled="
				+ enabled + ", admin=" + admin + ", phone=" + phone + "]";
	}
	
	public boolean phoneSet() {
		if (this.phone != null) {
			return true;
		} else
			return false;
	}
}
