package com.syncpower.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SyncpowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyncpowerApplication.class, args);
	}

}
