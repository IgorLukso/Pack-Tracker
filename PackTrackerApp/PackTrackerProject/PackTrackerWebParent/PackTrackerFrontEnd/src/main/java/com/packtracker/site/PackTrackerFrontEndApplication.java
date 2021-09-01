package com.packtracker.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.packtracker.common.entity","com.packtracker.site.user"})
public class PackTrackerFrontEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PackTrackerFrontEndApplication.class, args);
	}

}
