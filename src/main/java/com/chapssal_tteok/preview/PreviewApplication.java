package com.chapssal_tteok.preview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PreviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreviewApplication.class, args);
	}

}
