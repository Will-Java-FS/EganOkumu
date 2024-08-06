package com.revature.Project0.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.revature.Project0")
@EntityScan("com.revature.Project0.models")
@EnableJpaRepositories("com.revature.Project0.repositories")
public class Project0Application {

	public static void main(String[] args) {
		SpringApplication.run(Project0Application.class, args);
	}

}
