package com.example.virtualGallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VirtualGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualGalleryApplication.class, args);
	}

}
