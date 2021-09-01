package com.packtracker.site;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// MVC configuration class to expose pet photos stored on the local file system

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String dirName = "pet-photos";
		Path petPhotoDir = Paths.get(dirName);
		
		// absolute path name
		String petPhotosPath = petPhotoDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/" +dirName+ "/**")
			.addResourceLocations("file:/" +petPhotosPath+ "/");
	}
}


