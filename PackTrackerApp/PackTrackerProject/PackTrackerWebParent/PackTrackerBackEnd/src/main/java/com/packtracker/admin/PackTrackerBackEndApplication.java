package com.packtracker.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.packtracker.common.entity","com.packtracker.admin.user"})
public class PackTrackerBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PackTrackerBackEndApplication.class, args);
	}

}
