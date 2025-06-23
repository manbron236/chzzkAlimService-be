package com.chzzknotify.chzzk_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ChzzkBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChzzkBackendApplication.class, args);
	}

}
